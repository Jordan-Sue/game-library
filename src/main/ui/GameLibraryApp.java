package ui;

import exceptions.NegativePlayTimeException;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.buttons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Class implementation based on SimpleDrawingPlayerComplete

// Visual application for game library
public class GameLibraryApp extends JFrame {

    private static final String JSON_STORE = "./data/gameLibrary.json";
    public static final int WIDTH = 854;
    public static final int HEIGHT = 480;

    private JPanel panel;
    private GameLibrary gameLib;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the visual game library application
    public GameLibraryApp() {
        super("Game Library");
        init();
        initializeGraphics();
        initializeMainMenu();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the game library
    private void init() {
        panel = new JPanel();
        gameLib = new GameLibrary();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where this GameLibrary will operate
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Have to change for pop up "are you sure?" ???
        setLocationRelativeTo(null);
//        addComponentListener(new ComponentAdapter() {
//            public void componentResized(ComponentEvent componentEvent) {
//                System.out.println("I think");
//                System.out.println(getWidth());
//                System.out.println(getHeight());
//            }
//        });
    }

    // MODIFIES: this
    // EFFECTS: creates the buttons for the main menu
    private void initializeMainMenu() {
        panel.setLayout(new GridLayout(3, 2, getWidth() / 16, getHeight() / 16));
        panel.setPreferredSize(new Dimension(getWidth() / 2, getHeight() / 2));

        new AddButton(this, panel, "Add Game");

        new RemoveButton(this, panel, "Remove Game");

        new FindButton(this, panel, "Find Game");

        new ExploreButton(this, panel, "Explore Your Library");

        new SaveButton(this, panel, "Save");

        new LoadButton(this, panel, "Load");

        panel.setVisible(true);
        add(panel, BorderLayout.SOUTH);
    }

    // EFFECTS: creates the pop-up dialogue box to add a game to the library
    public void addGameDialogueBox() {
        Status [] statuses = { Status.Not_Played, Status.Beaten, Status.Played, Status.Completed };
        JComboBox<Status> statusInput = new JComboBox<>(statuses);
        JTextField nameInput = new JTextField();
        JTextField systemInput = new JTextField();
        JTextField playTimeInput = new JTextField();

        JPanel addPanel = new JPanel(new GridLayout(0, 1));

        addPanel.add(new JLabel("Name"));
        addPanel.add(nameInput);

        addPanel.add(new JLabel("System"));
        addPanel.add(systemInput);

        addPanel.add(new JLabel("Status"));
        addPanel.add(statusInput);

        addPanel.add(new JLabel("Play Time"));
        addPanel.add(playTimeInput);

        int option = JOptionPane.showConfirmDialog(this,
                addPanel,
                "Enter the details of the game you wish to add",
                JOptionPane.OK_CANCEL_OPTION);
        processAddGame(option, nameInput, systemInput, statusInput, playTimeInput);
    }

    // MODIFIES: this
    // EFFECTS: processes the inputs from the addGameDialogueBox and adds the game to the gameLibrary
    private void processAddGame(int option, JTextField nameInput, JTextField systemInput,
                                JComboBox<Status> statusInput, JTextField playTimeInput) {
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameInput.getText();
                String system = systemInput.getText();
                Status status = (Status) statusInput.getSelectedItem();
                double playTime = Double.parseDouble(playTimeInput.getText());
                gameLib.addGame(new Game(name, system, status, playTime));
                if (playTime < 0) {
                    throw new NegativePlayTimeException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "You did not enter a valid number for play time",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (NegativePlayTimeException e) {
                JOptionPane.showMessageDialog(this,
                        "Cannot enter a negative play time",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a game from the game library
    public void removeGame() {
        System.out.println("I can't remove");
    }

    // EFFECTS: finds a game
    public void findGame() {
        System.out.println("I can't find");
    }

    // MODIFIES: this
    // EFFECTS: displays all the games in the game library
    public void exploreLibrary() {
        System.out.println("I can't explore");
    }

    // EFFECTS: saves current GameLibrary to a JSON file
    public void saveGameLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(gameLib);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved Game Library to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                    "Unable to write to the file " + JSON_STORE,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads a GameLibrary from a file
    public void loadGameLibrary() {
        try {
            gameLib = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Successfully loaded Game Library from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Unable to load from the file " + JSON_STORE,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
