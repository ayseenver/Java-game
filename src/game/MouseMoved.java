/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.bodies.Crosshair;
import city.cs.engine.WorldView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * @author Ayse Enver, ayse.enver@city.ac.uk
 */
public class MouseMoved implements MouseMotionListener {

    private final WorldView view;
    private final Crosshair crosshair;

    public MouseMoved(WorldView view) {
        this.view = view;
        crosshair = new Crosshair(view.getWorld());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Move the crosshair to the current mouse position.
     * @param e description of the key event
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        crosshair.setPosition(view.viewToWorld(e.getPoint()));
    }
}
