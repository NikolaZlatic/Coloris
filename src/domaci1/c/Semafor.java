/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaci1.c;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Zlatic
 */
public class Semafor extends Rectangle{
    
    public Semafor(int x, int y){
        
        super(x,y,250,120);
        setStroke(Color.GRAY);
        setStrokeWidth(15);
        
    }
    
}
