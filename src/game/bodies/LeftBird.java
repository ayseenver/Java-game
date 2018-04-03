/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bodies;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.World;

/**
 * @author Ayse Enver, ayse.enver@city.ac.uk
 */
public class LeftBird extends Bird {

    private static final BodyImage image
            = new BodyImage("data/leftBird.png", 3);

    private static final Shape shape = new PolygonShape(-1.44f, 0.79f, 0.41f, 1.22f, 1.38f, 0.29f,
            0.18f, -1.21f, -1.46f, 0.7f);

    private static final BodyImage featherImage
            = new BodyImage("data/feather.png", 1);

    public LeftBird(World w) {
        super(w);
        SolidFixture fixture = new SolidFixture(this, shape);
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
        Feather feather = new Feather(this.getWorld(), player, ground, 1);
        feather.setPosition(this.getPosition());
        feather.addImage(featherImage);
        this.destroy();
    }

    /**
     * Returns the amount of lives the bird has remaining
     * <p>
     * When the bird is clicked, the lives of the bird are decremented. This
     * method returns the number of lives remaining.
     *
     * @return The number of lives remaining.
     */
    @Override
    public int getLives() {
        return lives;
    }
}
