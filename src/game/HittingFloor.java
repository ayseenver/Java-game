package game;

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

    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Feather && e.getOtherBody() == floor) {
            player.decrementLives();
            e.getReportingBody().destroy();
        }
    }
}