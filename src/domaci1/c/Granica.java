/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaci1.c;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Zlatic
 */
public class Granica extends Polygon{
    
    public Granica(){
        super(150,20,
        150,580,
        420,580,
        420,20,
        390,20,
        390,550,
        180,550,
        180,20);
        setFill(Color.GRAY);
    }
    
}
