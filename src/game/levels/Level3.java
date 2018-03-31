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
import game.bodies.LeftBird;
import game.bodies.RightBird;
import game.bodies.TwitterBird;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Ayse
 */
public class Level3 extends GameLevel {

    private ArrayList<Bird> birds;
    private SoundClip gameMusic;

    public void populate(Game game) {
        super.populate(game);

        playSound();

        birds = new ArrayList<>();

        drawGroundAndWalls();

        // make a platform
        Shape platformShape = new BoxShape(4, 0.5f);
        Body platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(9, 3.5f));
        platform1.setAngleDegrees(30);

        drawBirds();
    }

    @Override
    public void drawBirds() {
        Bird leftBird = new LeftBird(this);
        leftBird.setPosition(new Vec2(0, 8));
        birds.add(leftBird);

        Bird rightBird = new RightBird(this);
        rightBird.setPosition(new Vec2(6, 8));
        birds.add(rightBird);

        Bird twitterBird = new TwitterBird(this);
        twitterBird.setPosition(new Vec2(-6, 8));
        birds.add(twitterBird);
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
            gameMusic = new SoundClip("data/music/Space Lounge Loop.wav");   // Open an audio input stream
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
