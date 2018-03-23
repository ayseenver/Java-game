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
import city.cs.engine.WorldView;
import game.Feather;

/**
 *
 * @author Ayse
 */
public class RightBird extends Bird {

    public WorldView view;
    private static final BodyImage image
            = new BodyImage("data/rightBird.png", 3);

    private static final Shape shape = new PolygonShape(-0.77f, 1.21f, 1.2f, 0.03f, 1.46f,
            -0.13f, 0.75f, -0.96f, -1.03f, -1.34f, -1.46f, -0.87f, -1.44f, 0.69f);

    private static final BodyImage featherImage
            = new BodyImage("data/feather2.png", 1);

    public RightBird(World w) {
        super(w);

        SolidFixture fixture = new SolidFixture(this, shape);
        addImage(image);
    }

    @Override
    public void birdClicked(Player player, Body ground) {
        decrementLives();
        Feather feather = new Feather(this.getWorld(), player, ground, 2);
        feather.setPosition(this.getPosition());
        feather.addImage(featherImage);
        this.destroy();
    }

    @Override
    public int getLives() {
        return lives;
    }
}
