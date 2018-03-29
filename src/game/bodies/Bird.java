/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bodies;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.Walker;
import city.cs.engine.World;
import java.util.Timer;
import java.util.TimerTask;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Ayse
 */
public abstract class Bird extends Walker {

    int lives = 1;

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            fly();
        }
    };

    public Bird(World w) {
        super(w);
        timer.scheduleAtFixedRate(task, 0, 50);
    }

    public void fly() {
        if(this.getWorld().isRunning()){
            if (this.getPosition().y < 8 && this.getPosition().y < 8.5) {
                this.applyImpulse(new Vec2(0, 3));
            }
        }
    }

    public void decrementLives() {
        lives--;
    }

    public abstract void birdClicked(Player player, Body ground);

    public abstract int getLives();
}
