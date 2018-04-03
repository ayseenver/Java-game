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
public class TwitterBird extends Bird {

    private static final BodyImage image
            = new BodyImage("data/twitterBird.png", 3);

    private static final Shape shape = new PolygonShape(0.57f, 1.05f, 1.48f, 0.31f, 1.48f,
            0.06f, 0.54f, -0.82f, -0.47f, -1.05f, -1.47f, -0.52f, -1.07f, 0.8f, 0.52f, 1.06f);

    private static final BodyImage featherImage
            = new BodyImage("data/feather3.png", 1);

    public TwitterBird(World w) {
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
     * @param player The current player in the level. This is so the collision
     * listener Pickup can be added to the feather.
     * @param ground The ground/floor of the current level. This is so the
     * collision listener HittingFloor can be added to the feather.
     */
    @Override
    public void birdClicked(Player player, Body ground) {
        decrementLives();
        Feather feather = new Feather(this.getWorld(), player, ground, 3);
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
