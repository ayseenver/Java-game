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
 * @author Ayse Enver, ayse.enver@city.ac.uk
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

    /**
     * Handles when a bird is clicked on by the user.
     * <p>
     * When a bird is clicked, it is removed from a list of birds that are being
     * tracked, so that the method knows how many birds remain in the level. The
     * bird is also destroyed through its own birdClicked method.
     *
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        for (Bird bird : birds) {
            if (bird.contains(view.viewToWorld(e.getPoint()))) {

                bird.birdClicked(player, ground);

                if (bird.lives == 0) {
                    birds.remove(bird);
                }

                break;
            }
        }
    }
}
