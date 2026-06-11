package app.tiles;

import app.GamePanel;
import javax.swing.*;
import java.awt.*;

public class GrassTile extends Tile{
    public GrassTile(int tileX, int tileY){
      
     super(tileX,tileY); //como grasstile hereda tile entonces uso super en lugar de this para constructor creo

    }
    
    private void dibujarGrass(Graphics g, int x, int y, int width,int height){
      g.setColor(Color.GREEN);
      g.fillRect(x,y,width,height);
     for (int granos = 0; granos < width/4; granos++) {
        for (int granosY = 0; granosY < height/4; granosY++) {
          g.setColor(new Color(0,100,0));
          g.drawRect(x,y,width-granos,height-granosY);  
        }
      }

    }

    public void draw(Graphics g, int x, int y) {
        // aquí va el dibujo del tile, relativo a (x, y)
     int width = xt*GamePanel.TILE_SIZE;
     int height = yt*GamePanel.TILE_SIZE;
     dibujarGrass(g,x,y,width,height);
    }
}
