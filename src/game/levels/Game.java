package game.levels;

import city.cs.engine.*;
import game.ControlPanel;
import game.bodies.BirdClicker;
import game.bodies.Player;
import game.Controller;
import game.EnterName;
import game.GameOver;
import game.GiveFocus;
import game.HighScoreReader;
import game.HighScoreWriter;
import game.MouseMoved;
import game.MyView;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * @author Ayse Enver, ayse.enver@city.ac.uk
 */
public class Game {

    private GameLevel world;
    private final UserView view;
    private int level;
    private final Controller controller;
    private Player oldPlayer;
    private final ControlPanel buttons;
    private final GameOver gameOver;
    private final EnterName enterName;
    private JFrame mainFrame;
    private JFrame nameFrame;
    private JFrame gameOverFrame;

    public Game() {

        level = 1;
        world = new Level1();
        world.populate(this);

        view = new MyView(world, 500, 500);

        // uncomment this to draw a 1-metre grid over the view      
        // view.setGridResolution(1);
        buttons = new ControlPanel(world);
        gameOver = new GameOver(world);
        enterName = new EnterName(this);

        createMainFrame();
        createNameFrame();
        createGameOverFrame();

        controller = new Controller(world.getPlayer());
        mainFrame.addKeyListener(controller);

        view.addMouseMotionListener(new MouseMoved(view));

        // uncomment to make the view track the player
        // world.addStepListener(new Tracker(view, world.getPlayer()));
        // uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(world, 500, 500);
        view.addMouseListener(new BirdClicker(this.view, world.getBirds(),
                world.getPlayer(), world.getGround()));

        // start!
        world.start();
    }

    /**
     * Triggers the "game over" state of the game.
     * <p>
     * When the player runs out of lives or reaches the end of the game, this
     * method will trigger to bring up the "game over" screen. The "game over"
     * screen contains a list of the top 5 players and their scores, as well as
     * the player with the highest score. This is handed here with
     * HighScoreReader and HighScoreWriter.
     */
    public void doGameOver() throws IOException {
        getMainFrame().setVisible(false);
        getGameOverFrame().setVisible(true);

        HighScoreWriter writer = new HighScoreWriter("data/scores.txt");
        writer.writeHighScore(getPlayer().getName(), getPlayer().getScore());

        HighScoreReader reader = new HighScoreReader("data/scores.txt");
        reader.setGameOver(gameOver);
        reader.readScores();
    }

    public void createNameFrame() {
        nameFrame = new JFrame("Enter name");

        nameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nameFrame.setLocationByPlatform(true);
        nameFrame.add(enterName);
        nameFrame.setResizable(false);
        nameFrame.pack();
        nameFrame.setVisible(true);
        nameFrame.requestFocus();
        view.addMouseListener(new GiveFocus(nameFrame));
    }

    public void createGameOverFrame() {
        gameOverFrame = new JFrame("Game over");

        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setLocationByPlatform(true);
        gameOverFrame.add(gameOver);
        gameOverFrame.setResizable(false);
        gameOverFrame.pack();
        gameOverFrame.setVisible(false);
        gameOverFrame.requestFocus();
        view.addMouseListener(new GiveFocus(gameOverFrame));
    }

    public void createMainFrame() {
        mainFrame = new JFrame("acwg276Game - ms3");
        mainFrame.add(buttons, BorderLayout.WEST);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationByPlatform(true);
        mainFrame.add(view);
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setVisible(false);
        mainFrame.requestFocus();
        view.addMouseListener(new GiveFocus(mainFrame));
    }

    public JFrame getNameFrame() {
        return nameFrame;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JFrame getGameOverFrame() {
        return gameOverFrame;
    }

    /**
     * Returns the player that exists in the current world (level of the game).
     * <p>
     * Used primarily when a level progresses to update the player with the
     * stats of the player in the level before.
     *
     * @return The player that exists in the current world (game level).
     */
    public Player getPlayer() {
        return world.getPlayer();
    }

    /**
     * Checks if level completion circumstances have been met.
     * <p>
     * This is usually when all of the birds in a level have been destroyed.
     * This is checked within the level class itself, using the isCompleted()
     * method.
     *
     * @return True of false if level completion circumstances have been met.
     */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }

    /**
     * Moves the game on to the next level.
     * <p>
     * Takes the old player stats, then creates a new player and adds these
     * stats to it. Also updates the view so that it is now focusing on the new
     * level. Moves the level on by 1. If the player is on the last level, the
     * gameOver() method is called.
     *
     */
    public void goNextLevel() {
        world.getGameMusic().stop();
        oldPlayer = world.getPlayer();
        world.stop();
        switch (level) {
            case 0:
                level++;
                world = new Level1();
                buttons.setLevel(world);

                world.populate(this);

                controller.setBody(world.getPlayer());

                updatePlayer(oldPlayer);
                updateView();

                world.start();
                break;
            case 1:
                level++;
                world = new Level2();
                buttons.setLevel(world);

                world.populate(this);

                controller.setBody(world.getPlayer());

                updatePlayer(oldPlayer);
                updateView();

                world.start();
                break;
            case 2:
                level++;
                world = new Level3();
                buttons.setLevel(world);

                world.populate(this);

                controller.setBody(world.getPlayer());

                updatePlayer(oldPlayer);
                updateView();

                world.start();
                break;
            case 3:
                level++;
                world = new Level4();
                buttons.setLevel(world);

                world.populate(this);

                controller.setBody(world.getPlayer());

                updatePlayer(oldPlayer);
                updateView();

                world.start();
                break;
            default:
                try {
                    doGameOver();
                } catch (IOException e) {
                    System.exit(0);
                }
        }
    }

    /**
     * Moves the game back a level.
     * <p>
     * Takes the old player stats, then creates a new player and adds these
     * stats to it. Also updates the view so that it is now focusing on the new
     * level. Moves the level back by 1. If the player is on the first level,
     * the game is exited.
     */
    public void goPreviousLevel() {
        oldPlayer = world.getPlayer();
        world.stop();
        switch (level) {
            case 1:
                System.exit(0);
            case 2:
                level--;
                world = new Level1();
                buttons.setLevel(world);

                world.populate(this);

                controller.setBody(world.getPlayer());

                updatePlayer(oldPlayer);
                updateView();

                world.start();
                break;
            case 3:
                level--;
                world = new Level2();
                buttons.setLevel(world);

                world.populate(this);

                controller.setBody(world.getPlayer());

                updatePlayer(oldPlayer);
                updateView();

                world.start();
                break;

            case 4:
                level--;
                world = new Level3();
                buttons.setLevel(world);

                world.populate(this);

                controller.setBody(world.getPlayer());

                updatePlayer(oldPlayer);
                updateView();

                world.start();
                break;
        }
    }

    /**
     * Updates the new player with the stats of the old player.
     * <p>
     * A new player is created every time a new level is loaded. The stats of
     * the old player are saved and here they are added to the new player, so
     * that scores etc can carry across levels.
     *
     */
    private void updatePlayer(Player oldPlayer) {
        world.getPlayer().setName(oldPlayer.getName());
        world.getPlayer().setFeatherCount(oldPlayer.getFeatherCount());
        world.getPlayer().setScore(oldPlayer.getScore());
        world.getPlayer().setLives(oldPlayer.getLives());
    }

    /**
     * Updates the view to focus on the new level.
     * <p>
     * Updates the view so that it is displaying the new level. Also adds all
     * the listeners back into the scene.
     *
     */
    private void updateView() {
        //Set the view to the new world.
        view.setWorld(world);
        view.addMouseListener(new BirdClicker(this.view, world.getBirds(),
                world.getPlayer(), world.getGround()));
        view.addMouseMotionListener(new MouseMoved(view));
    }

    /**
     * Sets the level the game should load/be on.
     * <p>
     * Used primarily in conjunction with GoNextLevel() when a level is selected
     * via the drop down box.
     *
     * @param level The level that the game should now be on.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    public static void main(String[] args) {
        new Game();
    }
}
