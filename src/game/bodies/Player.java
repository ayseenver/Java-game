package game.bodies;

import city.cs.engine.*;

/**
 * Simple character
 */
public class Player extends Walker {

    private static final Shape shape = new PolygonShape(-0.185f, 1.125f, -0.58f, 0.729f,
            -0.646f, -0.312f, -0.417f, -1.094f, 0.435f, -1.09f, 0.681f, -0.316f, 0.479f, 0.918f);

    private static final BodyImage image
            = new BodyImage("data/character.png", 2.25f);

    private int featherCount = 0;
    private int score = 0;
    private int lives = 3;

    public Player(World world) {
        super(world, shape);
        addImage(image);
    }

    public int getFeatherCount() {
        return featherCount;
    }

    public void setFeatherCount(int featherCount) {
        this.featherCount = featherCount;
    }

    public void incrementFeatherCount() {
        featherCount++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore(int featherScore) {
        score += featherScore;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void decrementLives() {
        lives--;
    }

}
