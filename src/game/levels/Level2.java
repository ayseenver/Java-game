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
 * @author Ayse Enver, ayse.enver@city.ac.uk
 */
public class Level2 extends GameLevel {

    private ArrayList<Bird> birds;
    private SoundClip gameMusic;

    /**
     * Sets up the level.
     * <p>
     * Adds the player to the level, along with the exit door. Also adds the
     * background song, position of the birds and any extra walls that should
     * exist in this level.
     *
     * @param game The game that the level should exist in.
     */
    public void populate(Game game) {
        super.populate(game);

        playTrack();

        birds = new ArrayList<Bird>();

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

    /**
     * Draws all the birds in the scene.
     * <p>
     * The birds drawn differ from level to level.
     *
     */
    @Override
    public void drawBirds() {

        for (int i = 0; i < 4; i++) {
            Bird rightBird = new RightBird(this);
            rightBird.setPosition(new Vec2(-8 + (i * 6), 8));
            birds.add(rightBird);
        }
    }

    /**
     * Returns the starting position of the player.
     * 
     */
    @Override
    public Vec2 startPosition() {
        return new Vec2(-8, -10);
    }

    /**
     * Returns the starting position of the exit door.
     * 
     */
    @Override
    public Vec2 doorPosition() {
        return new Vec2(10, -9.7f);
    }

    /**
     * Checks if the level has been completed.
     * <p>
     * This returns true when all birds in the level have been destroyed.
     */
    @Override
    public boolean isCompleted() {
        return birds.isEmpty();
    }

    /**
     * Returns a list of birds in the scene.
     */
    @Override
    public ArrayList<Bird> getBirds() {
        return birds;
    }

    /**
     * Plays the level's background music.
     * <p>
     * The specific track being played will be different for each level, but
     * each level will play a track.
     *
     * Also handle exceptions if the track is not found etc.
     */
    @Override
    public void playTrack() {
        try {
            gameMusic = new SoundClip("data/music/Sci-fi Pulse Loop.wav");   // Open an audio input stream
            gameMusic.loop();  // Set it to continous playback (looping)
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Returns the track that is being played by the level.
     * <p>
     * This is mainly used when the level is being changed, so that the track
     * currently being played can be accessed and stopped.
     * 
     * @return The track (SoundClip) that is being played by the level currently.
     */
    @Override
    public SoundClip getGameMusic() {
        return gameMusic;
    }
}
