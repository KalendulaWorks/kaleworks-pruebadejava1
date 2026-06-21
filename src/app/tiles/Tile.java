package app.tiles;

import javax.swing.*;
import java.awt.*;
import app.func.*;
import app.util.*;
import app.*;

public abstract class Tile {

  protected MouseHandler mouseHandler;
  private int xt;
  private int xy;
  protected String nombre;

  public Tile() {
    mouseHandler = new MouseHandler();
  }

  public abstract void draw(Graphics g, int x, int y);

  public int getCoordXTile() {
    xt = mouseHandler.getX() / GamePanel.TILE_SIZE;
    return xt;
  }

  public int getCoordYTile() {
    xy = mouseHandler.getY() / GamePanel.TILE_SIZE;
    return xy;
  }

  public int getTilePX() {
    return mouseHandler.getX();
  }

  public int getTilePY() {
    return mouseHandler.getY();
  }
}
