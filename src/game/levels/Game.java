package game.levels;

import city.cs.engine.*;
import game.ControlPanel;
import game.bodies.BirdClicker;
import game.bodies.Player;
import game.Controller;
import game.GiveFocus;
import game.HighScoreReader;
import game.HighScoreWriter;
import game.MouseMoved;
import game.MyView;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;

public class Game {

    private GameLevel world;
    private final UserView view;
    private int level;
    private final Controller controller;
    private Player player;
    private Player oldPlayer;
    private final ControlPanel buttons;

    public Game() {

        level = 1;
        world = new Level1();
        world.populate(this);

        view = new MyView(world, 500, 500);

        // uncomment this to draw a 1-metre grid over the view      
        // view.setGridResolution(1);
        final JFrame frame = new JFrame("Event handling");

        buttons = new ControlPanel(world);
        frame.add(buttons, BorderLayout.WEST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.add(view);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.requestFocus();
        view.addMouseListener(new GiveFocus(frame));

        controller = new Controller(world.getPlayer());
        frame.addKeyListener(controller);

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

    public void gameOver() throws IOException {
        HighScoreWriter writer = new HighScoreWriter("data/scores.txt");
//        writer.clear();
        writer.writeHighScore("player1", this.getPlayer().getScore());

        HighScoreReader reader = new HighScoreReader("data/scores.txt");
        reader.readScores();
    }

    public Player getPlayer() {
        return world.getPlayer();
    }

    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }

    public void goNextLevel() {
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
                System.exit(0);
        }
    }

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

    private void updatePlayer(Player oldPlayer) {
        world.getPlayer().setFeatherCount(oldPlayer.getFeatherCount());
        world.getPlayer().setScore(oldPlayer.getScore());
        world.getPlayer().setLives(oldPlayer.getLives());
    }

    private void updateView() {
        //Set the view to the new world.
        view.setWorld(world);
        view.addMouseListener(new BirdClicker(this.view, world.getBirds(),
                world.getPlayer(), world.getGround()));
        view.addMouseMotionListener(new MouseMoved(view));
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static void main(String[] args) {
        new Game();
    }
}
