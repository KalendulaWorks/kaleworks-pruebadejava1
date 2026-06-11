package app.tiles;

import app.GamePanel;
import javax.swing.*;
import java.awt.*;

public class StudTile extends Tile{
    public StudTile(int tileX, int tileY){
      
     super(tileX,tileY); //como grasstile hereda tile entonces uso super en lugar de this para constructor creo

    }
    
    private void dibujarGrass(Graphics g, int x, int y, int width,int height){
      g.setColor(new Color(3, 40, 252));
      g.fillRect(x,y,width,height);
      g.setColor(new Color(5, 100, 156));
      g.fillOval(x+(width/4),y+(height/4),width/2,height/2);
      g.setColor(Color.BLACK);
      g.drawString("R", x+(width/2)-4, y+(height/2)+6);
    }

    public void draw(Graphics g, int x, int y) {
        // aquí va el dibujo del tile, relativo a (x, y)
     int width = xt*GamePanel.TILE_SIZE;
     int height = yt*GamePanel.TILE_SIZE;
     dibujarGrass(g,x,y,width,height);
    }
}
