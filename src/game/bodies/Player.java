package game.bodies;

import city.cs.engine.*;

/**
 * @author Ayse Enver, ayse.enver@city.ac.uk
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

    /**
     * Returns the amount of feathers collected.
     * <p>
     * Returns the amount of feathers collected by the player over the course of
     * the game so far.
     *
     * @return The number of feathers collected.
     */
    public int getFeatherCount() {
        return featherCount;
    }

    /**
     * Sets the feather count of the player.
     * <p>
     * Used primarily when a level progresses to update the player with the
     * stats of the player in the level before.
     *
     * @param featherCount The number of feathers to set the player's
     * featherCount to.
     */
    public void setFeatherCount(int featherCount) {
        this.featherCount = featherCount;
    }

    /**
     * Increments the player's feather count by 1
     * <p>
     * When the feather is collected, the feather count of the player is
     * incremented by 1. This method does that.
     */
    public void incrementFeatherCount() {
        featherCount++;
    }

    /**
     * Returns the player's score so far.
     * <p>
     * Returns the player's score so far.
     *
     * @return The player's score so far.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score of the player.
     * <p>
     * Used primarily when a level progresses to update the player with the
     * stats of the player in the level before.
     *
     * @param score The value to set the player's score to.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Increments the player's score by the value of the feather collected.
     * <p>
     * When the feather is collected, the score of the player is incremented by
     * the value of the feather collected. This method does that.
     *
     * @param featherScore The score associated with the feather collected.
     */
    public void incrementScore(int featherScore) {
        score += featherScore;
    }

    /**
     * Returns the player's lives remaining
     * <p>
     * Every time the player drops a feather, they will lose a life. This will
     * return the number of lives that remain.
     *
     * @return The player's lives remaining.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets the number of lives of the player.
     * <p>
     * Used primarily when a level progresses to update the player with the
     * stats of the player in the level before.
     *
     * @param lives The number of lives to set the player's life count to.
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Decrements the player's lives by 1.
     * <p>
     * When the feather is dropped, the player will lose a life. This method does that.
     *
     */
    public void decrementLives() {
        lives--;
    }

}
