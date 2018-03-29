/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bodies;

import city.cs.engine.Body;
import city.cs.engine.WorldView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Ayse
 */
public class BirdClicker extends MouseAdapter {

    private final WorldView view;
    private ArrayList<Bird> birds;
    private final Player player;
    private final Body ground;

    public BirdClicker(WorldView view, ArrayList<Bird> birds,
            Player player, Body ground) {
        this.view = view;
        this.birds = birds;
        this.player = player;
        this.ground = ground;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Bird bird : birds) {
            if (bird.contains(view.viewToWorld(e.getPoint()))) {

                bird.birdClicked(player, ground);
                
                if(bird.lives == 0){
                    birds.remove(bird);
                }
                
                break;
            }
        }
    }
}
