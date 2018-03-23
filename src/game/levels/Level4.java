/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import game.bodies.Bird;
import game.bodies.BossBird;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Ayse
 */
public class Level4 extends GameLevel {

    private ArrayList<Bird> birds;
    
    public void populate(Game game) {
        super.populate(game);

        birds = new ArrayList<>();

        drawGroundAndWalls();

        drawBirds();
    }

    @Override
    public void drawBirds() {
        Bird bossBird = new BossBird(this);
        bossBird.setPosition(new Vec2(0, 8));
        birds.add(bossBird);
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-8, -10);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(10, -9.7f);
    }

    @Override
    public boolean isCompleted() {
        return birds.isEmpty();
    }

    @Override
    public ArrayList<Bird> getBirds() {
        return birds;
    }
}
