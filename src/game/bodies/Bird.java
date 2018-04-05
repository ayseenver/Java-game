/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bodies;

import city.cs.engine.Body;
import city.cs.engine.Walker;
import city.cs.engine.World;
import java.util.Timer;
import java.util.TimerTask;
import org.jbox2d.common.Vec2;

/**
 * The abstract class for all birds.
 */
public abstract class Bird extends Walker {

    int lives = 1;

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            fly();
        }
    };

    /**
     * Constructor for the Bird class.
     * <p>
     * As this is an abstract class, this constructor will be used to create the
     * children of Bird (types of birds).
     *
     * @param w The world that the bird will be placed into.
     */
    public Bird(World w) {
        super(w);
        timer.scheduleAtFixedRate(task, 0, 50);
    }

    /**
     * Applies upwards force to the bird.
     * <p>
     * This is controlled by a timer so the force is applied at regular intervals.
     */
    public void fly() {
        if (this.getWorld().isRunning()) {
            if (this.getPosition().y < 8 && this.getPosition().y < 8.5) {
                this.applyImpulse(new Vec2(0, 3));
            }
        }
    }

    /**
     * Decrements the bird's lives by 1
     * <p>
     * When the bird is clicked, the lives of the bird are decremented by 1.
     * This method does that.
     */
    public void decrementLives() {
        lives--;
    }

    /**
     * Destroys the bird and places a feather in its place.
     * <p>
     * When the bird is clicked, the lives of the bird are decremented and a
     * feather is created at the bird's position. The bird is then destroyed.
     *
     * @param player The current player in the level.
     * @param ground The ground/floor of the current level.
     */
    public abstract void birdClicked(Player player, Body ground);

    /**
     * Returns the amount of lives remaining
     * <p>
     * When the bird is clicked, the lives of the bird are decremented. This
     * method returns the number of lives remaining.
     */
    public abstract int getLives();
}
