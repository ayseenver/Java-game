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
 *
 * @author Ayse
 */
public class Level1 extends GameLevel {

    private ArrayList<Bird> birds;
    private SoundClip gameMusic;

    @Override
    public void populate(Game game) {
        super.populate(game);

        playSound();

        birds = new ArrayList<>();

        drawGroundAndWalls();

        drawBirds();
    }

    @Override
    public void drawBirds() {
        for (int i = 0; i < 3; i++) {
            Bird leftBird = new LeftBird(this);
            leftBird.setPosition(new Vec2(-6 + (i * 6), 8));
            birds.add(leftBird);
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
            gameMusic = new SoundClip("data/music/Off Limits.wav");   // Open an audio input stream
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
