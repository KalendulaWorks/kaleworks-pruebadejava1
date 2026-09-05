package app.func;

import app.util.*;
import app.*;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class ActualizarGUI {
  private GamePanel panel;
  private MouseHoverHandler mouseHoverHandler;

  public ActualizarGUI(GamePanel panel, MouseHoverHandler mouseHoverHandler) {
    this.panel = panel;
    this.mouseHoverHandler = mouseHoverHandler;
  }

  public void updateGUI() {

  }

  public void drawGui(Graphics g) {
    GUIoverlay(g);
  }

  private void GUIoverlay(Graphics g) {
    int overlayCol = mouseHoverHandler.gTileCol();
    int overlayRow = mouseHoverHandler.gTileRow();
    int overlayCols = 1;
    int overlayRows = 1;

    int x = overlayCol * GamePanel.TILE_SIZE;
    int y = overlayRow * GamePanel.TILE_SIZE;
    int w = overlayCols * GamePanel.TILE_SIZE;
    int h = overlayRows * GamePanel.TILE_SIZE;

    g.setColor(new Color(214, 211, 118, 155));
    g.fillRect(x, y, w, h);

  }
}
