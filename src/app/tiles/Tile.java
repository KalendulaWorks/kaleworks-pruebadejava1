package app.tiles;

import javax.swing.*;
import java.awt.*;

public abstract class Tile {

  protected int xt;
  protected int yt;

  public Tile() {

  }

  public abstract void draw(Graphics g, int x, int y);
}
