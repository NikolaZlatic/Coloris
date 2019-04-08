/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaci1.c;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Zlatic
 */

public class Figura {
    
    private static int a = 30;
    private Rectangle[] polja;
    private boolean active;
    
    public boolean isActive(){
        return this.active;
    }

    private static Color[] izbor = {
        Color.WHITE,
        Color.YELLOW,
        Color.BLUE,
        Color.RED,
        Color.CYAN
    };
    
    public Figura(int n){
        polja = new Rectangle[3];
        for(int i = 0; i < 3 ; i++){
            polja[i] = new Rectangle(70 + n * 200, (a * i) + 100 - (n * 70), a, a);
            int j = (int) (Math.random() * 5);
            polja[i].setFill(izbor[j]);
        }
       if(n == 0) active = false;
       else active = true;
        
    }
    
    public void dole(){
        for(int i = 0 ; i < 3 ; i++){
            polja[i].setTranslateY(polja[i].getTranslateY() + 10);
        }
    }
    
    public void levo(){
        for(int i = 0 ; i < 3 ; i++){
            polja[i].setTranslateX(polja[i].getTranslateX() - 30);
        }
    }
    
    public void desno(){
        for(int i = 0 ; i < 3 ; i++){
            polja[i].setTranslateX(polja[i].getTranslateX() + 30);
        }
    }
    
    public Rectangle getPolje(int i ){
        if(i>=0 && i<=3){
            return polja[i];
        }
        return null;
    }
    
    public void promenaBoja(){
        Color temp = (Color) polja[0].getFill();
        polja[0].setFill(polja[1].getFill());
        polja[1].setFill(polja[2].getFill());
        polja[2].setFill(temp);
        
    }
    
    public double getDonjaGranica(){
        Bounds boundsInScene = polja[2].localToScene(polja[2].getBoundsInLocal());
        return boundsInScene.getMaxY();
    }
    
    public double getGornjaGranica(){
        Bounds boundsInScene = polja[0].localToScene(polja[0].getBoundsInLocal());
        return boundsInScene.getMinY();
    }
    
    public double getLevaGranica(){
        Bounds boundsInScene = polja[2].localToScene(polja[2].getBoundsInLocal());
        return boundsInScene.getMinX();
    }
    
    public double getDesnaGranica(){
        Bounds boundsInScene = polja[2].localToScene(polja[2].getBoundsInLocal());
        return boundsInScene.getMaxX();
    }
    
    public void setActive(){
        this.active = false;
    }
}
