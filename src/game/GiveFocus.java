package game;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A mouse listener that gives the keyboard focus to a specified component.
 */
public class GiveFocus extends MouseAdapter {
    private final Component target;
    
    public GiveFocus(Component target) {
        this.target = target;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        target.requestFocus();
    }
}