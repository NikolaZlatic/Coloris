/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaci1.c;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Zlatic
 */


public class Slovo extends Text{
    
    public Slovo(String s , int i){
        setText(s);
        setFill(Color.RED);
        setStroke(Color.YELLOW);
        if(i == 5) setTranslateX(77.5); 
        else setTranslateX(65);
        setTranslateY(275 + i * 47.5);
        setFont(new Font("Copperplate Gothic Bold",60));
    }
    
}
