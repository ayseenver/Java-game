/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import city.cs.engine.SoundClip;
import game.bodies.Bird;
import game.bodies.LeftBird;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jbox2d.common.Vec2;

/**
 * @author Ayse Enver, ayse.enver@city.ac.uk
 */
public class Level1 extends GameLevel {

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
    @Override
    public void populate(Game game) {
        super.populate(game);

        playTrack();

        birds = new ArrayList<>();

        drawBirds();
    }

    /**
     * Draws all the birds in the scene.
     * <p>
     * The birds drawn differ from level to level.
     *
     */
    @Override
    public void drawBirds() {
        for (int i = 0; i < 3; i++) {
            Bird leftBird = new LeftBird(this);
            leftBird.setPosition(new Vec2(-6 + (i * 6), 8));
            birds.add(leftBird);
        }
    }

    /**
     * Returns the starting position of the player.
     */
    @Override
    public Vec2 startPosition() {
        return new Vec2(-8, -10);
    }

    /**
     * Starting position of the exit door.
     */
    @Override
    public Vec2 doorPosition() {
        return new Vec2(10, -9.7f);
    }

    /**
     * Checks if the level has been completed.
     * <p>
     * This returns true when all birds in the level have been destroyed.
     *
     * @return True if the level is complete, False if not.
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
            gameMusic = new SoundClip("data/music/Off Limits.wav");   // Open an audio input stream
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
