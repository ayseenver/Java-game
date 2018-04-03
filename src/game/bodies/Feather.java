/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bodies;

import city.cs.engine.Body;
import city.cs.engine.DynamicBody;
import city.cs.engine.PolygonShape;
import city.cs.engine.SolidFixture;
import city.cs.engine.SoundClip;
import city.cs.engine.World;
import game.HittingFloor;
import game.Pickup;
import game.bodies.Player;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Ayse Enver, ayse.enver@city.ac.uk
 */
public class Feather extends DynamicBody {

    private SoundClip gameMusic;

    PolygonShape shape = new PolygonShape(0.371f, 0.492f, 0.16f, 0.436f, -0.049f, 0.283f, -0.299f, -0.043f, -0.482f, -0.465f,
            -0.072f, -0.205f, 0.115f, -0.086f, 0.477f, 0.482f);
    SolidFixture fixture = new SolidFixture(this, shape);

    public Feather(World w, Player player, Body ground, int score) {
        super(w);
        this.addCollisionListener(new Pickup(player, score));
        this.addCollisionListener(new HittingFloor(ground, player));
    }

    /**
     * Plays a sound when the feather is picked up.
     * <p>
     * Triggers a short sound to be played once whenever a feather is picked up
     * by the player.
     *
     * Also handles exceptions if the track is not found etc.
     */
    public void playSound() {

        try {
            gameMusic = new SoundClip("data/featherSound.wav");   // Open an audio input stream
            gameMusic.play();  // Play once
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
}
