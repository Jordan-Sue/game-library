package ui;

import model.*;

import java.util.Scanner;

// Class implementation based on Teller Project

// Application for game library
public class GameListApp {
    private GameList gameLib;
    private Scanner input;

    // EFFECTS: runs the game library application
    public GameListApp() {
        runGameList();
    }

    // MODIFIES: this
    // EFFECTS: executes appropriate action based on user input
    private void runGameList() {
        boolean running = true;
        String userInput;

        input = new Scanner(System.in);

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

        System.out.println("Quiting...");

    }

    // MODIFIES: this
    // EFFECTS: executes a process based on user input
    private void processMainMenu(String i) {
        switch (i) {
            case "a":
                startAddGame();
                break;
            case "r":
                System.out.println("That feature is pending");
                break;
            case "f":
                startFindGame();
                break;
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
        System.out.println("q -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a game to the game library
    private void startAddGame() {
        System.out.println("Enter the name of the game: ");
        String name = input.nextLine();
        if (gameLib.findGame(name)) {
            System.out.println("You already added that game\n");
            startAddGame();
        } else {
            System.out.println("Enter the system the game is on: ");
            String system = input.nextLine();

            System.out.println("Enter your completion status: ");
            String status = input.nextLine();

            System.out.println("Enter your play time: ");
            double playTime = input.nextDouble();

            Game newGame = new Game(name, system, status, playTime);
            gameLib.addGame(newGame);
            System.out.println("Game added");
        }
    }

    // EFFECTS: finds a game and
    private void startFindGame() {
        System.out.print("Enter the name of the game you wish to find: ");
        String name = input.nextLine();
        if (gameLib.findGame(name)) {
            showGameMenu();
            String userInput;

            userInput = input.nextLine();
            processGameMenu(name, userInput);
        }

    }

    private void showGameMenu() {
        System.out.println("\nGame Menu");
        System.out.println("s -> edit completion status");
        System.out.println("p -> edit play time");
    }

    private void processGameMenu(String name, String i) {
        Game g = gameLib.returnGame(name);
        if (i.equals("s")) {
            System.out.println("Enter new completion status: ");
            String status = input.nextLine();
            g.changeStatus(status);
            System.out.println("Status changed");
        } else if (i.equals("p")) {
            System.out.println("Enter new play time: ");
            double playTime = input.nextDouble();
            g.changePlayTime(playTime);
            System.out.println("Play time changed");
        } else {
            System.out.println("\"" + i + "\"" + " is not a valid command");
        }
    }
}
