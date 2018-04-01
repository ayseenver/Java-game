package game;

import city.cs.engine.*;
import game.bodies.Player;

/**
 * Collision listener that allows the player to collect things.
 */
public class Pickup implements CollisionListener {

    private final Player player;
    private final int score;

    public Pickup(Player player, int score) {
        this.player = player;
        this.score = score;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Feather && e.getOtherBody() == player) {

            Feather feather = (Feather) e.getReportingBody();
            
            player.incrementFeatherCount();
            player.incrementScore(score);
            feather.playSound();
            feather.destroy();
        }
    }
}
