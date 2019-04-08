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
public class Broj extends Text{
    
    private String text;
    private int vrednost;
    
    public Broj(int x , int i){
        setTranslateX(540);
        setTranslateY(85 + i * 210);
        setFill(Color.GRAY);
        text = "000000" + x;
        vrednost = x;
        setText(text.substring(text.length()-6,text.length()));
        if(i == 2){
            setText(text.substring(text.length()-2,text.length()-1) + "         " + text.substring(text.length()-1,text.length()));
            setTranslateX(550 );
        }

        setFont(new Font("Copperplate Gothic Bold",40));
    }
    
    public void setVrednostScore(){

        vrednost+=3;
        
        text = "000000" + vrednost;;
        setText(text.substring(text.length()-6,text.length()));
    }
    
}
