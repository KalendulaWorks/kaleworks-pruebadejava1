package app;
import javax.swing.*;
import java.awt.*;
import app.tiles.Tile;
import app.tiles.GrassTile;
import app.tiles.SandTile;
import app.tiles.StudTile;

public class GamePanel extends JPanel {
  
  public static final int TILE_SIZE = 32;
  private int mapCols = 40;
  private int mapRows = 20;
  
  private Tile[][] cuadros;

  public GamePanel(){
    this.setPreferredSize(new Dimension(1280,720));
    this.setBackground(Color.DARK_GRAY);
    
    cuadros = new Tile[mapCols][mapRows];
      
      for (int col = 0; col < mapCols; col++){
        for (int row = 0; row < mapRows; row++){
          cuadros[col][row] = new StudTile(1,1);
        }
      }
   }
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);   
      for (int col = 0; col < mapCols; col++){  
        for (int row = 0; row < mapRows; row++){
          int x = col * TILE_SIZE;
          int y = row * TILE_SIZE;
          cuadros[col][row].draw(g,x,y);
        }
      }
      
      g.setColor(Color.BLACK);
      for (int col = 0; col <= mapCols; col++){
       int x = col * TILE_SIZE;
        g.drawLine(x,0,x,mapRows*TILE_SIZE);
      }
      
      for (int row = 0; row <= mapRows; row++) {
       int y = row * TILE_SIZE;
        g.drawLine(0,y,mapCols*TILE_SIZE,y);
      }

  }

}   
