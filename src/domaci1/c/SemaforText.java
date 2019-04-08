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
public class SemaforText extends Text{
    
    public SemaforText(String s, int i){
        setText(s);
        if(i == 1 ) setTranslateX(600);
        else setTranslateX(585);
        if(i == 2) setTranslateX(530);
        setTranslateY(125 + i*210);
        setFill(Color.GRAY);
        setFont(new Font("Copperplate Gothic Bold",20));
    }
    
}
