package game;

import game.bodies.Feather;
import city.cs.engine.*;
import game.bodies.Player;

/**
 * Collision listener that allows the bird to collect things.
 */
public class HittingFloor implements CollisionListener {

    private final Body floor;
    private final Player player;

    public HittingFloor(Body floor, Player player) {
        this.floor = floor;
        this.player = player;
    }

    /**
     * Handle collisions between feather and floor.
     * <p>
     * When the feather hits the floor, the player will lose a life.
     *
     * @param e description of the key event
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Feather && e.getOtherBody() == floor) {
            player.decrementLives();
            e.getReportingBody().destroy();
        }
    }
}
