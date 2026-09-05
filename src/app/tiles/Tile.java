package app.tiles;

import app.util.*;
import java.awt.*;

public abstract class Tile {

  protected MouseHandler mouseHandler;
  protected String[] tileInfo;
  protected boolean hasStructure;
  
  

  public Tile() {

  }

  public abstract void draw(Graphics g, int x, int y);

  protected void infoDeTile(String nombre, String nombreT, String desc) {
    this.tileInfo = new String[] { nombre, nombreT, desc };
  }

  public String[] gTileData() {
    return tileInfo;
  }

}
