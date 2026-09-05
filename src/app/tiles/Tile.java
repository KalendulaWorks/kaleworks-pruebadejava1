package app.tiles;

import javax.swing.*;
import java.awt.*;
import app.func.*;
import app.util.*;
import app.*;

public abstract class Tile {

  protected MouseHandler mouseHandler;
  protected String[] tileInfo;

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
