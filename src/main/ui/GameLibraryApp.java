package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Class implementation and structure based on Teller Project, JSON based on JsonSerializationDemo

// Application for game library
public class GameLibraryApp {
    private static final String JSON_STORE = "./data/gamelibrary.json";
    private GameLibrary gameLib;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the game library application
    public GameLibraryApp() {
        runGameList();
    }

    // MODIFIES: this
    // EFFECTS: executes appropriate action based on user input
    private void runGameList() {
        boolean running = true;
        String userInput;

        init();

        System.out.println("\nWelcome to your Game Library");

        while (running) {
            showMainMenu();

            userInput = input.nextLine();
            userInput = userInput.toLowerCase();

            if (userInput.equals("q")) {
                running = false;
            } else {
                processMainMenu(userInput);
            }
        }

        System.out.println("\nQuiting...");
    }

    // MODIFIES: this
    // EFFECTS: initializes the game list
    private void init() {
        gameLib = new GameLibrary();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: executes a process based on user input
    private void processMainMenu(String i) {
        switch (i) {
            case "a":
                startAddGame();
                break;
            case "r":
                startRemoveGame();
                break;
            case "f":
                startFindGame();
                break;
            case "s":
                startSaveGameLibrary();
                break;
            case "l":
                startLoadGameLibrary();
            default:
                System.out.println("\"" + i + "\"" + " is not a valid command");
                break;
        }
    }

    // EFFECTS: displays the valid inputs to the user
    private void showMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("a -> add a game");
        System.out.println("r -> remove a game");
        System.out.println("f -> find a game");
        System.out.println("s -> save Game Library");
        System.out.println("l -> load Game Library");
        System.out.println("q -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a game to the game library
    private void startAddGame() {
        System.out.println("\nEnter the name of the game:");
        String name = input.nextLine();

        if (!gameLib.findGame(name)) {
            System.out.println("\nEnter the system the game is on:");
            String system = input.nextLine();

            showInputMenu();
            Status status = processInputMenu();

            System.out.println("\nEnter your play time:");
            Game newGame = new Game(name, system, status, 0);
            startChangePlayTime(newGame);

            gameLib.addGame(newGame);
            System.out.println("Game added");
        } else {
            System.out.println("That game is already in your library");
        }
    }

    // EFFECTS: displays valid inputs for completion statuses
    private void showInputMenu() {
        System.out.println("\nChoose your completion status:");
        System.out.println("1: Not Played");
        System.out.println("2: Played");
        System.out.println("3: Beaten");
        System.out.println("4: Completed");
    }

    // EFFECTS: returns the status that was chosen,
    //          otherwise tells the user the choice was invalid and lets them pick again
    private Status processInputMenu() {
        String choice = input.nextLine();
        switch (choice) {
            case "1":
                return Status.Not_Played;
            case "2":
                return Status.Played;
            case "3":
                return Status.Beaten;
            case "4":
                return Status.Completed;
            default:
                System.out.println("Not a valid choice");
                processInputMenu();
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: removes a game from the game library,
    //          otherwise the user is told the game is not in the library
    private void startRemoveGame() {
        System.out.println("\nWhich game do you want to remove?");
        String game = input.nextLine();
        if (gameLib.findGame(game)) {
            System.out.println(gameLib.returnGame(game).getName() + " was removed");
            gameLib.removeGame(game);
        } else {
            System.out.println("That game doesn't exist in you library");
        }
    }

    // MODIFIES: this
    // EFFECTS: finds a game and shows another menu
    private void startFindGame() {
        System.out.print("\nEnter the name of the game you wish to find:\n");
        String name = input.nextLine();
        if (gameLib.findGame(name)) {
            showGameMenu();
            String userInput = input.nextLine();
            processGameMenu(name, userInput);
        } else {
            System.out.println("That game is not in your library");
        }
    }

    // EFFECTS: displays valid inputs for the game menu
    private void showGameMenu() {
        System.out.println("\nGame Menu");
        System.out.println("s -> edit completion status");
        System.out.println("p -> edit play time");
        System.out.println("b -> back to main menu");
    }

    // EFFECTS: executes a process based on the valid inputs from the game menu
    private void processGameMenu(String name, String i) {
        Game game = gameLib.returnGame(name);
        switch (i) {
            case "s":
                startChangeStatus(game);
                break;
            case "p":
                System.out.println("\nEnter new play time:");
                startChangePlayTime(game);
                System.out.println("Play time changed");
                break;
            case "b":
                return;
            default:
                System.out.println("\"" + i + "\"" + " is not a valid command");
                break;
        }
    }

    // MODIFIES: game
    // EFFECTS: changes the status of the given game
    private void startChangeStatus(Game game) {
        showInputMenu();
        game.changeStatus(processInputMenu());
        System.out.println("Status changed");
    }

    // MODIFIES: game
    // EFFECTS: changes the playTime of the given game
    private void startChangePlayTime(Game game) {
        try {
            double playTime = input.nextDouble();
            game.changePlayTime(playTime);
            input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Enter a valid number");
            input.nextLine();
            startChangePlayTime(game);
        }
    }

    // EFFECTS: saves current GameLibrary to a JSON file
    private void startSaveGameLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(gameLib);
            jsonWriter.close();
            System.out.println("Saved Game Library to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to the file " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads a GameLibrary from a file
    private void startLoadGameLibrary() {
        try {
            gameLib = jsonReader.read();
            System.out.println("Successfully loaded Game Library from " + JSON_STORE);
            input.nextLine();
        } catch (IOException e) {
            System.out.println("Unable to load from the file " + JSON_STORE);
        }
    }
}
