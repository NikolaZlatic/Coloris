/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaci1.c;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Zlatic
 */ //Nikola nema pojma dap
public class Domaci1C extends Application{
    
    private static int width = 800;
    private static int height = 600;
    private Pane root = new Pane();
    private static boolean gameOver;
    private Broj brScore;
    private static Broj brLast;
    private static int brojProvera;
    
    private Rectangle [][] polja;
    
    public List<Figura> figure;
    
    private Parent createContext(){
        root.setPrefSize(width, height);
        return root;
    }
    
    public static void main(String[] args) {
       launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        gameOver = false;
        brojProvera = 0;
        
        polja = new Rectangle[7][20];
        figure = new ArrayList<>();
        
        Scene scene = new Scene(createContext());
        stage.setScene(scene);
        stage.show();
        
        Rectangle pozadina = new Rectangle(0,0,width,height);
        pozadina.setFill(Color.BLACK);
        root.getChildren().add(pozadina);
        
        Rectangle plavaPozadina = new Rectangle(150,20,260,560);
        plavaPozadina.setFill(Color.DARKBLUE);
        root.getChildren().add(plavaPozadina);
        
        Figura fig = new Figura(0);
        figure.add(fig);
        
        root.getChildren().addAll(fig.getPolje(0));
        root.getChildren().addAll(fig.getPolje(1));
        root.getChildren().addAll(fig.getPolje(2));
        
        Slovo c  = new Slovo("C" , 0);
        Slovo o  = new Slovo("O" , 1);
        Slovo l  = new Slovo("L" , 2);
        Slovo o2 = new Slovo("O" , 3);
        Slovo r  = new Slovo("R" , 4);
        Slovo i  = new Slovo("I" , 5);
        Slovo s  = new Slovo("S" , 6);
        
        root.getChildren().addAll(c,o,l,o2,r,i,s);
        
        Granica g = new Granica();
        root.getChildren().add(g);
        
        Semafor score = new Semafor(500, 27);
        root.getChildren().add(score);
        SemaforText textScore = new SemaforText("SCORE", 0);
        root.getChildren().add(textScore);
        
        Semafor last = new Semafor(500, 240);
        root.getChildren().add(last);
        SemaforText textLast = new SemaforText("LAST", 1);
        root.getChildren().add(textLast);
        
        Semafor brake = new Semafor(500, 453);
        root.getChildren().add(brake);
        SemaforText textBrake = new SemaforText("BRAKE          RAW", 2);
        root.getChildren().add(textBrake);
        
        brScore = new Broj(0,0);
        root.getChildren().add(brScore);
        
        brLast = new Broj(0,1);
        root.getChildren().add(brLast);
        
        Broj brRaw = new Broj(11,2);
        root.getChildren().add(brRaw);
        
        doradaGranice();
        
        novaFigura();
        
        moveFigureOnkeyPressed(scene);
        
        stage.setTitle("Domaci2D");
        
    }
    
    private void zameniBoje(){
        Figura figStara = figure.get(figure.size() - 1);
        Figura figNova = figure.get(0);
        
        Color temp = (Color) figStara.getPolje(0).getFill();
        figStara.getPolje(0).setFill(figNova.getPolje(0).getFill());
        figNova.getPolje(0).setFill(temp);
        temp = (Color) figStara.getPolje(1).getFill();
        figStara.getPolje(1).setFill(figNova.getPolje(1).getFill());
        figNova.getPolje(1).setFill(temp);
        temp = (Color) figStara.getPolje(2).getFill();
        figStara.getPolje(2).setFill(figNova.getPolje(2).getFill());
        figNova.getPolje(2).setFill(temp);

    }
    
    private void novaFigura(){
        Figura fig = new Figura(1);
        root.getChildren().addAll(fig.getPolje(0));
        root.getChildren().addAll(fig.getPolje(1));
        root.getChildren().addAll(fig.getPolje(2));
        figure.add(fig);
        zameniBoje();
    }
    
    private void doradaGranice(){
        Rectangle zuta1 = new Rectangle(150,120,30,10);
        zuta1.setFill(Color.YELLOW);
        root.getChildren().add(zuta1);
        
        Rectangle zuta2 = new Rectangle(390,120,30,10);
        zuta2.setFill(Color.YELLOW);
        root.getChildren().add(zuta2);
        
        for(int i = 4 ; i < 12 ; i++){
            Line noval = new Line(150,i*10,180,i*10);
            root.getChildren().add(noval);
            Line novad = new Line(390,i*10,420,i*10);
            root.getChildren().add(novad);
        }
        
        for(int i = 14 ; i < 55 ; i++){
            Line noval = new Line(150,i*10,180,i*10);
            root.getChildren().add(noval);
            Line novad = new Line(390,i*10,420,i*10);
            root.getChildren().add(novad);
        }
        
        Line donjal = new Line(150,580,180,550);
        root.getChildren().add(donjal);
        
        Line donjad = new Line(390,550,420,580);
        root.getChildren().add(donjad);
        
        for(int i = 19 ; i < 39 ; i++){
            Line nova = new Line(i*10,550,i*10,580);
            root.getChildren().add(nova);
        }
    }
    
    private Figura aktivnaFigura(){
        for(int i = 0 ; i < figure.size() ; i++){
            if(figure.get(i).isActive() == true) return figure.get(i);
        }
        return null;
    }
    
    private void pomeriAktivnuDole(Figura fig){
        fig.dole();
    }
    
    private void pomeriAktivnuDesno(Figura fig){
        fig.desno();
    }
    
    private void pomeriAktivnuLevo(Figura fig){
        fig.levo();
    }
    
    private void moveFigureOnkeyPressed(Scene root) {
    root.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case UP:  
              if(!gameOver)
              aktivnaFigura().promenaBoja();
              break;
          case RIGHT: 
              if(!gameOver && nemaPreklapanjaDesno()) {
                double X=root.getX()+aktivnaFigura().getDesnaGranica();
                if(aktivnaFigura().getDesnaGranica() < 390)
                pomeriAktivnuDesno(aktivnaFigura());
              }
              break;
          case DOWN:  
              if(!gameOver) {
                if(aktivnaFigura().getDonjaGranica() == 550 || proveraSudaraNaDole()) {
                        dodajAktivnuUListNaTabli();
                        aktivnaFigura().setActive();
                    try {
                        proveraTriIsteVodoravno();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Domaci1C.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        if(!gameOver)
                            novaFigura();
                        else gameOver();
                }
                else {
                    pomeriAktivnuDole(aktivnaFigura());               
                }
              }
              break;
          case LEFT:  
              if(aktivnaFigura().getLevaGranica() > 180 && !gameOver && nemaPreklapanjalevo())
              pomeriAktivnuLevo(aktivnaFigura());
              break;
        }
      }
    });
  }
    
    private boolean nemaPreklapanjaDesno(){
        for (int i = 0 ; i < figure.size() ; i++){
            if(figure.get(i).getDonjaGranica() > aktivnaFigura().getGornjaGranica() 
               && figure.get(i).getGornjaGranica() < aktivnaFigura().getDonjaGranica() 
               && aktivnaFigura().getDesnaGranica() == figure.get(i).getLevaGranica()) {
                return false;
            }
        }
        return true;
    }
    
    private boolean nemaPreklapanjalevo(){
        for (int i = 0 ; i < figure.size() ; i++){
           
            if(figure.get(i).getDonjaGranica() > aktivnaFigura().getGornjaGranica() 
               && figure.get(i).getGornjaGranica() < aktivnaFigura().getDonjaGranica() 
               && aktivnaFigura().getLevaGranica() == figure.get(i).getDesnaGranica()) 
                    return false;
        }
        return true;
    }
    
    private void gameOver(){
        Text gameOverText = new Text();
        gameOverText.setText("GAME OVER");
        gameOverText.setTranslateX(182);
        gameOverText.setTranslateY(80);
        gameOverText.setFont(new Font("Copperplate Gothic Bold",30));
        gameOverText.setFill(Color.YELLOW);
        root.getChildren().add(gameOverText);
    }
    
    private void dodajAktivnuUListNaTabli() {
        for(int i = 0 ; i < 3 ; i ++){
            Bounds boundsInScene = aktivnaFigura().getPolje(i).localToScene(aktivnaFigura().getPolje(i).getBoundsInLocal());
            double x = boundsInScene.getMinX() - 180;
            double y = boundsInScene.getMinY() - 20;
            polja[(int)x / 30][(int)y / 30] = aktivnaFigura().getPolje(i);
        }
        if(aktivnaFigura().getGornjaGranica() < 120) gameOver = true;
    }
    
    private boolean proveraSudaraNaDole(){
        
        for(int i = 0 ; i < 7 ; i ++){
            for (int j = 0 ; j < 20 ; j++){
                if(polja[i][j] != null) {
                    if(aktivnaFigura().getDonjaGranica() == polja[i][j].localToScene(polja[i][j].getBoundsInLocal()).getMinY() 
                    && aktivnaFigura().getLevaGranica() == polja[i][j].localToScene(polja[i][j].getBoundsInLocal()).getMinX()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private void proveraTriIsteUspravno() throws InterruptedException{
        boolean t = false;
        brojProvera++;
        while(!t){
            for(int i = 0 ; i < 7 ; i ++){
                for(int j = 0 ; j < 20 ; j ++){
                    t = true;
                    if(polja[i][j] != null && polja[i][j+1] != null && polja[i][j+2] != null) {
                        if(polja[i][j].getFill().equals(polja[i][j+1].getFill()) && polja[i][j].getFill().equals(polja[i][j+2].getFill())) {
                            brojProvera = 0;
                            int m =3;
                            if(polja[i][j+3] != null)
                                if(polja[i][j].getFill().equals(polja[i][j+3].getFill())){
                                    m++;
                                    if(polja[i][j+4] != null)
                                        if(polja[i][j].getFill().equals(polja[i][j+4].getFill()))
                                            m++;
                                }
                            sklanjanjeUspravnoVise(i,j+m-1,m);
                            Thread.sleep(500);
                            brScore.setVrednostScore();
                            t = false;
                        }
                    }
                }
            }
        }
        if(brojProvera < 2) proveraTriIsteVodoravno();
    }
    
    private void sklanjanjeUspravno(int i,int j) {
        root.getChildren().remove(polja[i][j]);
        root.getChildren().remove(polja[i][j-1]);
        root.getChildren().remove(polja[i][j-2]);
        for(int n = j ; n > 3 ; n --){
            
            polja[i][n] = polja[i][n-3];
            
            if(polja[i][n]!=null){
                polja[i][n].setTranslateY(polja[i][n].getTranslateY() + 90);
            }
            
        }  
        
    }
    
    private void sklanjanjeUspravnoVise(int i,int j, int k) {
        for(int m = 0 ; m < k ; m++)
            root.getChildren().remove(polja[i][j-m]);
        //root.getChildren().remove(polja[i][j]);
        //root.getChildren().remove(polja[i][j-1]);
        //root.getChildren().remove(polja[i][j-2]);
        for(int n = j ; n > k ; n --){
            
            polja[i][n] = polja[i][n-k];
            
            if(polja[i][n]!=null){
                polja[i][n].setTranslateY(polja[i][n].getTranslateY() + 30*k);
            }
            
        }  
        
    }
    
    private void proveraTriIsteVodoravno() throws InterruptedException{
        boolean t = false;
        brojProvera++;
        while(!t){
            for(int i = 0 ; i < 5 ; i ++){
                for(int j = 0 ; j < 20 ; j ++){
                    t = true;
                    if(polja[i][j] != null && polja[i+1][j] != null && polja[i+2][j] != null) {
                        if(polja[i][j].getFill().equals(polja[i+1][j].getFill()) && polja[i][j].getFill().equals(polja[i+2][j].getFill())) {
                            int k = 3;
                            if(i<4 && polja[i+3][j] != null)
                            if(polja[i][j].getFill().equals(polja[i+3][j].getFill())) {
                                k++;
                                if(i<3 && polja[i+4][j] != null)
                                if(polja[i][j].getFill().equals(polja[i+4][j].getFill()))
                                    k++;
                            }
                            sklanjanjeVodoravnoVise(i,j,k);
                            Thread.sleep(500);
                            brScore.setVrednostScore();
                            t = false;
                            brojProvera = 0;
                        }
                    }
                }
            }
        }
        proveraTriIsteUspravno();
    }
    
    private void sklanjanjeVodoravnoVise(int i , int j, int k) {
        for(int m = 0 ; m < k ; m++){
            root.getChildren().remove(polja[i+m][j]);
        }
        
        for(int n = j ; n > 0 ; n --){
            for(int m = 0 ; m < k ; m ++){
                polja[i+m][n] = polja[i+m][n-1];
                if(polja[i+m][n]!=null){
                    polja[i+m][n].setTranslateY(polja[i+m][n].getTranslateY() + 30);
                }
            }/*
            polja[i][n] = polja[i][n-1];
            polja[i+1][n] = polja[i+1][n-1];
            polja[i+2][n] = polja[i+2][n-1];
            
            if(polja[i][n]!=null){
                polja[i][n].setTranslateY(polja[i][n].getTranslateY() + 30);
            }
            
            if(polja[i+1][n]!=null){
                polja[i+1][n].setTranslateY(polja[i+1][n].getTranslateY() + 30);
            }
            
            if(polja[i+2][n]!=null){
                polja[i+2][n].setTranslateY(polja[i+2][n].getTranslateY() + 30);
            }
*/
        }
    }
    
    private void sklanjanjeVodoravno(int i , int j) {
        root.getChildren().remove(polja[i][j]);
        root.getChildren().remove(polja[i+1][j]);
        root.getChildren().remove(polja[i+2][j]);
        for(int n = j ; n > 0 ; n --){
            
            polja[i][n] = polja[i][n-1];
            polja[i+1][n] = polja[i+1][n-1];
            polja[i+2][n] = polja[i+2][n-1];
            
            if(polja[i][n]!=null){
                polja[i][n].setTranslateY(polja[i][n].getTranslateY() + 30);
            }
            
            if(polja[i+1][n]!=null){
                polja[i+1][n].setTranslateY(polja[i+1][n].getTranslateY() + 30);
            }
            
            if(polja[i+2][n]!=null){
                polja[i+2][n].setTranslateY(polja[i+2][n].getTranslateY() + 30);
            }

        }
    }
    
}
