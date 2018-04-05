package game.levels;

import city.cs.engine.*;
import game.bodies.Bird;
import game.bodies.Player;
import game.Door;
import game.DoorListener;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;

/**
 * The abstract class that all levels will inherit from.
 */
public abstract class GameLevel extends World {

    private Player player;
    private Body ground;
    private Game game;
    private SoundClip gameMusic;

    public Player getPlayer() {
        return player;
    }

    /**
     * Sets up the level.
     * <p>
     * Adds the player to the level, along with the exit door. The enemies and
     * walls are added in each level by that level's class (Level1.java etc).
     *
     * @param game The game that the level should exist in.
     */
    public void populate(Game game) {
        this.game = game;
        player = new Player(this);
        player.setPosition(startPosition());
        Door door = new Door(this);
        door.setPosition(doorPosition());
        door.addCollisionListener(new DoorListener(game));
        drawGroundAndWalls();
    }

    /**
     * Returns the starting position of the player.
     *
     */
    public abstract Vec2 startPosition();

    /**
     * Returns the starting position of the exit door.
     *
     */
    public abstract Vec2 doorPosition();

    /**
     * Checks if the level has been completed.
     * <p>
     * This returns true when all birds in the level have been
     * destroyed.
     *
     */
    public abstract boolean isCompleted();

    /**
     * Returns a list of birds in the scene.
     *
     */
    public abstract ArrayList<Bird> getBirds();

    /**
     * Draws all the birds in the scene.
     * <p>
     * The birds drawn differ from level to level.
     *
     */
    public abstract void drawBirds();

    /**
     * Returns the floor/ground of the level.
     *
     */
    public Body getGround() {
        return ground;
    }

    /**
     * Draws the ground and walls of the level.
     * <p>
     * These are the same in each level, so this method can be defined in the
     * GameLevel abstract class.
     *
     */
    public void drawGroundAndWalls() {
        // make the ground
        Shape groundShape = new BoxShape(11, 0.5f);
        ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -11.5f));

        // walls
        Shape wallShape = new BoxShape(0.5f, 6);
        Body leftWall = new StaticBody(this, wallShape);
        leftWall.setPosition(new Vec2(-11.5f, -6));
        Body rightWall = new StaticBody(this, wallShape);
        rightWall.setPosition(new Vec2(11.5f, -6));
    }

    /**
     * Returns the game that this level exists in.
     * 
     */
    public Game getGame() {
        return game;
    }

    /**
     * Plays the level's background music.
     * <p>
     * The specific track being played will be different for each level, but
     * each level will play a track.
     */
    public abstract void playTrack();

    /**
     * Returns the track that is being played by the level.
     * <p>
     * This is mainly used when the level is being changed, so that the track
     * currently being played can be accessed and stopped.
     */
    public abstract SoundClip getGameMusic();
}
