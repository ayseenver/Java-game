package game.levels;

import city.cs.engine.*;
import game.bodies.Bird;
import game.bodies.Player;
import game.Door;
import game.DoorListener;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;

/**
 * A level of the game.
 */
public abstract class GameLevel extends World {

    private Player player;
    private Body ground;
    private Game game;
    private SoundClip gameMusic;

    public Player getPlayer() {
        return player;
    }

    public void populate(Game game) {
        this.game = game;
        player = new Player(this);
        player.setPosition(startPosition());
        Door door = new Door(this);
        door.setPosition(doorPosition());
        door.addCollisionListener(new DoorListener(game));
    }

    public abstract Vec2 startPosition();

    public abstract Vec2 doorPosition();

    public abstract boolean isCompleted();

    public abstract ArrayList<Bird> getBirds();

    public abstract void drawBirds();

    public Body getGround() {
        return ground;
    }

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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public abstract void playSound();

    public abstract SoundClip getGameMusic();
}
