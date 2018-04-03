/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bodies;

import java.util.Random;
import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * @author Ayse Enver, ayse.enver@city.ac.uk
 */
public class BossBird extends Bird {

    Random rand = new Random();

    private static final BodyImage image
            = new BodyImage("data/bossBird.png", 4);

    private static final Shape shape = new PolygonShape(0.45f, 1.98f, 1.84f, 0.02f, 0.15f, -1.7f,
            -0.72f, -1.93f, -1.74f, 0.17f, -1.81f, 1.23f, -0.08f, 1.93f);

    private static final BodyImage featherImage
            = new BodyImage("data/feather4.png", 1);

    public BossBird(World w) {
        super(w);
        SolidFixture fixture = new SolidFixture(this, shape);

        this.lives = 3;

        addImage(image);
    }

    /**
     * Destroys the bird and places a feather in its place.
     * <p>
     * When the bird is clicked, the lives of the bird are decremented. If there
     * are no lives remaining, the bird is destroyed and a feather created in
     * its place. Since this type of bird has 1 life, it will be destroyed on
     * first click.
     *
     * @param player The current player in the level. This is so the collision listener
     * Pickup can be added to the feather.
     * @param ground The ground/floor of the current level. This is so the collision listener
     * HittingFloor can be added to the feather.
     */
    @Override
    public void birdClicked(Player player, Body ground) {
        decrementLives();
        if (lives == 0) {
            Feather feather = new Feather(this.getWorld(), player, ground, 10);
            feather.setPosition(this.getPosition());
            feather.addImage(featherImage);
            this.destroy();
        } else {
            int n = rand.nextInt(8);
            this.setPosition(new Vec2(n, this.getPosition().y));
        }
    }

    /**
     * Applies upwards force to the bird.
     * <p>
     * This is controlled by a timer so the force is applied at regular
     * intervals.
     */
    @Override
    public void fly() {
        if (this.getWorld().isRunning()) {
            if (this.getPosition().y < 8 && this.getPosition().y < 8.5) {
                this.applyImpulse(new Vec2(0, 6.5f));
            }
        }
    }

    /**
     * Returns the amount of lives the bird has remaining
     * <p>
     * When the bird is clicked, the lives of the bird are decremented. This
     * method returns the number of lives remaining.
     * @return lives The number of lives remaining.
     */
    @Override
    public int getLives() {
        return lives;
    }
}
