package app;

import javax.swing.*;
import java.awt.*;

public class GrassTile {
    private int xt = 1;
    private int yt = 1; //tamaños en formato tiles
    
    public GrassTile(int tilex, int tiley){
     this.xt = tilex;
     this.yt = tiley;
    }
    
    private void dibujarGrass(Graphics g, int x, int y, int width,int height){
      g.setColor(Color.GREEN);
      g.fillRect(x,y,width,height);
      g.setColor(new Color(0,100,0));
      g.drawRect(x,y,width-1,height-1);
    }

    public void draw(Graphics g, int x, int y) {
        // aquí va el dibujo del tile, relativo a (x, y)
     int width = xt*GamePanel.TILE_SIZE;
     int height = yt*GamePanel.TILE_SIZE;
     dibujarGrass(g,x,y,width,height);
    }
}
