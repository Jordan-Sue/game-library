package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.buttons.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Class implementation based on SimpleDrawingPlayerComplete, JSON based on JsonSerializationDemo

// Visual application for GameLibrary
public class GameLibraryApp extends JFrame {

    private static final String JSON_STORE = "./data/gameLibrary.json";
    public static final int WIDTH = 854;
    public static final int HEIGHT = 480;

    private JPanel panel;
    private Status[] statuses;
    private GameLibrary gameLib;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the visual GameLibrary application
    public GameLibraryApp() {
        super("Game Library");
        init();
        initializeGraphics();
        initializeMainMenu();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the GameLibrary application
    private void init() {
        panel = new JPanel();
        gameLib = new GameLibrary();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        statuses = new Status[]{Status.Not_Played, Status.Played, Status.Beaten, Status.Completed};
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where this GameLibrary will operate
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Have to change for pop up "are you sure?" ???
        setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: creates the buttons for the main menu
    private void initializeMainMenu() {
        panel.setLayout(new GridLayout(3, 2, getWidth() / 16, getHeight() / 16));
        panel.setPreferredSize(new Dimension(getWidth(), getHeight() / 2));

        new AddButton(this, panel, "Add Game");

        new RemoveButton(this, panel, "Remove Game");

        new FindButton(this, panel, "Find Game");

        new ExploreButton(this, panel, "Explore Your Library");

        new SaveButton(this, panel, "Save");

        new LoadButton(this, panel, "Load");

        add(panel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: creates the pop-up dialogue box to add a game to the library
    public void addGame() {
        JComboBox<Status> statusInput = new JComboBox<>(statuses);
        JTextField nameInput = new JTextField();
        JTextField systemInput = new JTextField();
        JTextField playTimeInput = new JTextField();

        JPanel addPanel = new JPanel(new GridLayout(0, 1));

        addPanel.add(new JLabel("Name"));
        addPanel.add(nameInput);

        addPanel.add(new JLabel("System"));
        addPanel.add(systemInput);

        addPanel.add(new JLabel("Completion Status"));
        addPanel.add(statusInput);

        addPanel.add(new JLabel("Play Time"));
        addPanel.add(playTimeInput);

        int okCancel = JOptionPane.showConfirmDialog(this,
                addPanel,
                "Add Game",
                JOptionPane.OK_CANCEL_OPTION);
        processAddGame(okCancel, nameInput, systemInput, statusInput, playTimeInput);
    }

    // MODIFIES: this, GameLib
    // EFFECTS: processes the inputs from the addGame and adds the game to the GameLibrary
    private void processAddGame(int okCancel, JTextField nameInput, JTextField systemInput,
                                JComboBox<Status> statusInput, JTextField playTimeInput) {
        if (okCancel == JOptionPane.OK_OPTION) {
            try {
                String name = nameInput.getText();
                String system = systemInput.getText();
                Status status = (Status) statusInput.getSelectedItem();
                double playTime = Double.parseDouble(playTimeInput.getText());
                if (playTime < 0) {
                    negativePlayTimeError();
                } else {
                    gameLib.addGame(new Game(name, system, status, playTime));
                    JOptionPane.showMessageDialog(this, "Game added");
                }
            } catch (NumberFormatException e) {
                invalidNumberError();
            }
        }
    }

    // MODIFIES: this, gameLib
    // EFFECTS: removes a game from the GameLibrary,
    //          if the game isn't in the library show a pop-up that tells the user
    public void removeGame() {
        JTextField nameInput = new JTextField();
        Object[] question = { "Enter the name of the game you wish to remove", nameInput };
        int okCancel = JOptionPane.showConfirmDialog(this,
                question,
                "Remove Game",
                JOptionPane.OK_CANCEL_OPTION);
        if (okCancel == JOptionPane.OK_OPTION) {
            String name = nameInput.getText();
            Game game = gameLib.returnGame(name);
            if (game == null) {
                JOptionPane.showMessageDialog(this,
                        "That game isn't in your library",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                gameLib.removeGame(game);
                JOptionPane.showMessageDialog(this, "Game removed");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: finds a game in the GameLibrary,
    //          if the game isn't in the library show a pop-up that tells the user
    public void findGame() {
        JTextField nameInput = new JTextField();
        Object[] question = { "Enter the name of the game you wish to find", nameInput };
        int okCancel = JOptionPane.showConfirmDialog(this,
                question,
                "Find Game",
                JOptionPane.OK_CANCEL_OPTION);
        if (okCancel == JOptionPane.OK_OPTION) {
            String name = nameInput.getText();
            Game game = gameLib.returnGame(name);
            if (game == null) {
                JOptionPane.showMessageDialog(this,
                        "That game isn't in your library",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                editGame(game);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: edits either the play time or the status of the game based off of the users choice
    private void editGame(Game game) {
        Object[] options = { "Edit Completion Status", "Edit Play Time", "Cancel" };
        int statusPlayCancel = JOptionPane.showOptionDialog(this,
                "Found " + game.getName() + ".\nWould you like to edit its completion status or change its play time?",
                "Edit Game",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);
        switch (statusPlayCancel) {
            case JOptionPane.YES_OPTION:
                editStatus(game);
                break;
            case JOptionPane.NO_OPTION:
                editPlayTime(game);
                break;
            default:
                break;
        }
    }

    // MODIFIES: this, game
    // EFFECTS: edits the status of the given game
    private void editStatus(Game game) {
        JComboBox<Status> newStatusInput = new JComboBox<>(statuses);
        JPanel editStatusPanel = new JPanel(new GridLayout(0, 1));
        editStatusPanel.add(new JLabel("Select a new status for " + game.getName()));
        editStatusPanel.add(newStatusInput);

        int okCancel = JOptionPane.showConfirmDialog(this,
                editStatusPanel,
                "Edit Completion Status",
                JOptionPane.OK_CANCEL_OPTION);

        if (okCancel == JOptionPane.OK_OPTION) {
            Status newStatus = (Status) newStatusInput.getSelectedItem();
            game.changeStatus(newStatus);
            JOptionPane.showMessageDialog(this, "Completion status changed");
        }
    }

    // MODIFIES: this, game
    // EFFECTS: edits the play time of the given game
    private void editPlayTime(Game game) {
        JTextField newPlayTimeInput = new JTextField();
        Object[] question = {"Enter the new play time for " + game.getName(), newPlayTimeInput};
        int okCancel = JOptionPane.showConfirmDialog(this,
                question,
                "Edit Play Time",
                JOptionPane.OK_CANCEL_OPTION);

        if (okCancel == JOptionPane.OK_OPTION) {
            try {
                double newPlayTime = Double.parseDouble(newPlayTimeInput.getText());

                if (newPlayTime < 0) {
                    negativePlayTimeError();
                } else {
                    game.changePlayTime(newPlayTime);
                    JOptionPane.showMessageDialog(this, "Play time changed");
                }
            } catch (NumberFormatException e) {
                invalidNumberError();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: display an error pop-up related to invalid inputs
    private void invalidNumberError() {
        JOptionPane.showMessageDialog(this,
                "Enter a valid number",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: display an error pop-up related to negative play time
    private void negativePlayTimeError() {
        JOptionPane.showMessageDialog(this,
                "Cannot enter a negative play time",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: displays all the games in the GameLibrary
    public void exploreLibrary() {
        setVisible(false);
        JFrame exploreFrame = new JFrame();
        exploreFrame.setLayout(new BorderLayout());
        exploreFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        exploreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        exploreFrame.setLocationRelativeTo(null);

        Object[][] games = gameLib.getGamesInfo();
        String[] columnNames = { "Name", "System", "Completion Status", "Playtime" };
        JTable gameLibTable = new JTable(games, columnNames);
        gameLibTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(gameLibTable);
        Dimension size = new Dimension(exploreFrame.getWidth(),
                (exploreFrame.getHeight() - (exploreFrame.getHeight() / 4)));
        scrollPane.setPreferredSize(size);

        JPanel explorePanel = new JPanel();
        new SortButton(this, explorePanel, "Sort");
        new StatsButton(this, explorePanel, "Stats");

        JSplitPane explorePane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, explorePanel);
        explorePane.setDividerLocation(exploreFrame.getHeight() - 85);

        exploreFrame.add(explorePane);
        exploreFrame.setVisible(true);
    }

    // MODIFIES: explorePane
    // EFFECTS: sorts the list of games displayed
    public void sortGameLib() {
        gameLib.sort();
        exploreLibrary();
    }

    // MODIFIES: this
    // EFFECTS: displays a congratulation image if there is a completed game in the GameLibrary,
    //          otherwise tells the user that there is no completed game
    public void showStats() {
        if (gameLib.isComplete()) {
            String sep = System.getProperty("file.separator");
            ImageIcon congrats = new ImageIcon(System.getProperty("user.dir") + sep + "images" + sep + "congrats.png");
            JOptionPane.showMessageDialog(this,
                    "You have a completed game!",
                    "Congratulations!",
                    JOptionPane.PLAIN_MESSAGE,
                    congrats);
        } else {
            JOptionPane.showMessageDialog(this, "You have no completed games");
        }
    }

    // MODIFIES: this
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

    // MODIFIES: this, gameLib
    // EFFECTS: loads a GameLibrary from a file
    public void loadGameLibrary() {
        try {
            gameLib = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Loaded Game Library from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Unable to load from the file " + JSON_STORE,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
