package game;

import city.cs.engine.UserView;
import game.bodies.Bird;
import game.levels.GameLevel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;

public class MyView extends UserView {

    private static final Image heart = new ImageIcon("data/heart.png").getImage();
    private static final Image heartBlue = new ImageIcon("data/heart2.png").getImage();
    private static final Image background1 = new ImageIcon("data/background1.jpg").getImage();
    private static final Image background2 = new ImageIcon("data/background2.jpg").getImage();
    private static final Image background3 = new ImageIcon("data/background3.jpg").getImage();
    private static final Image background4 = new ImageIcon("data/background4.jpg").getImage();
    private int featherCount;
    private int lives;
    private int score;
    private GameLevel world;

    public MyView(GameLevel world, int width, int height) {
        super(world, width, height);
        this.world = world;
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        world = (GameLevel) this.getWorld();
        featherCount = world.getPlayer().getFeatherCount();
        score = world.getPlayer().getScore();
        lives = world.getPlayer().getLives();

        if (lives == 0) {
            g.drawString("Game over!", 220, 200);
            try {
                world.getGame().gameOver();
            } catch(IOException e){
                e.printStackTrace();
            }
            world.stop();
        }

        g.drawString("Feather count: " + featherCount, 0, 10);
        g.drawString("Score: " + score, 0, 20);

        //draw the player hearts
        for (int i = 0; i < lives; i++) {
            g.drawImage(heart, 460 - (i * 30), 0, 30, 30, this);
        }

        //draw the boss hearts on level 4
        if (this.getWorld().getClass().getName().equals("game.levels.Level4")) {
            for (Bird bird : world.getBirds()) {
                for (int i = 0; i < bird.getLives(); i++) {
                    g.drawImage(heartBlue, 250 - (i * 30), 0, 30, 30, this);
                }
            }
        }
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        switch (this.getWorld().getClass().getName()) {
            case "game.levels.Level1":
                g.drawImage(background1, 0, 0, this);
                break;
            case "game.levels.Level2":
                g.drawImage(background2, 0, 0, this);
                break;
            case "game.levels.Level3":
                g.drawImage(background3, 0, 5, this);
                break;
            case "game.levels.Level4":
                g.drawImage(background4, 0, 0, this);
                break;
            default:
                break;
        }
    }
}
