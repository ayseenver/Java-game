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
import game.Feather;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Ayse
 */
public class BossBird extends Bird{

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

    @Override
    public void birdClicked(Player player, Body ground) {
        decrementLives();
        System.out.println("Lives remaining: " + getLives());
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

    @Override
    public void fly() {
        if (this.getWorld().isRunning()) {
            if (this.getPosition().y < 8 && this.getPosition().y < 8.5) {
                this.applyImpulse(new Vec2(0, 6.5f));
            }
        }
    }

    @Override
    public int getLives() {
        return lives;
    }
}
