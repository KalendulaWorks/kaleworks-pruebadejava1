package app.tiles;

import javax.swing.*;
import java.awt.*;

public abstract class Tile {
   
   protected int xt;
   protected int yt;
   
   public Tile(int tileX,int tileY){
     this.xt = tileX;
     this.yt = tileY;
   }
   
   public abstract void draw(Graphics g, int x, int y);
}
