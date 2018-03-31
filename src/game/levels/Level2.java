/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import game.bodies.Bird;
import game.bodies.RightBird;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Ayse
 */
public class Level2 extends GameLevel {

    private ArrayList<Bird> birds;
    private SoundClip gameMusic;

    public void populate(Game game) {
        super.populate(game);

        playSound();

        birds = new ArrayList<Bird>();

        drawGroundAndWalls();

        drawBirds();

        // make two platforms
        Shape platformShape = new BoxShape(4, 0.5f);

        Body platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-9, 4.5f));
        platform1.setAngleDegrees(-10);

        Body platform2 = new StaticBody(this, platformShape);
        platform2.setPosition(new Vec2(9, 3.5f));
        platform2.setAngleDegrees(30);

        for (Bird bird : this.getBirds()) {
            bird.setFillColor(Color.white);
        }
    }

    @Override
    public void drawBirds() {

        for (int i = 0; i < 4; i++) {
            Bird rightBird = new RightBird(this);
            rightBird.setPosition(new Vec2(-8 + (i * 6), 8));
            birds.add(rightBird);
        }
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

    @Override
    public void playSound() {
        try {
            gameMusic = new SoundClip("data/music/Sci-fi Pulse Loop.wav");   // Open an audio input stream
            gameMusic.loop();  // Set it to continous playback (looping)
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    @Override
    public SoundClip getGameMusic() {
        return gameMusic;
    }
}
