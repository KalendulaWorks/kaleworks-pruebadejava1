package app;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
  
  public static final int TILE_SIZE = 32;
  private int mapCols = 20;
  private int mapRows = 15;
  
  public GamePanel(){
    this.setPreferredSize(new Dimension(1280,720));
    this.setBackground(Color.DARK_GRAY);
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(Color.BLACK);

      for (int col = 0; col <= mapCols; col++) { //con este for dibujo las lineas verticales de la cuadricula
       int x = col * TILE_SIZE;
        g.drawLine(x,0,x,mapRows*TILE_SIZE);
      }
      
      for (int row = 0; row <= mapRows; row++) {
       int y = row * TILE_SIZE;
        g.drawLine(0,y,mapCols*TILE_SIZE,y);
      }

  }

}   
