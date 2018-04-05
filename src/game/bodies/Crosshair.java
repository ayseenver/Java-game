/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.PolygonShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

/**
 * An image that follows the mouse cursor.
 */
public class Crosshair extends StaticBody{
    
    public Crosshair(World w) {
        super(w);
        
        PolygonShape shape = new PolygonShape(0.035f,0.498f, 0.49f,0.041f, 0.492f,-0.035f, 0.037f,-0.494f, 
                -0.039f,-0.496f, -0.498f,-0.029f, -0.5f,0.037f, -0.039f,0.5f);
        GhostlyFixture fixture = new GhostlyFixture(this, shape);
        this.addImage(new BodyImage("data/crosshair.png", 1));
    }  
}


