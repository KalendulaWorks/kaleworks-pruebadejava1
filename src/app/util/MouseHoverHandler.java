package app.util;

import app.*;
import app.func.*;
import app.tiles.Tile;

public class MouseHoverHandler {
  private MouseHandler mouseHandler;
  private GamePanel panel;
  private HoverTile hoverTile;
  private MapaManager mapaManager;

  public MouseHoverHandler(GamePanel panel) {
    this.panel = panel;
    this.mouseHandler = panel.getMouseHandler();
    this.hoverTile = new HoverTile();
    this.mapaManager = panel.getMapaManager();
  }

  public void ActualizarMouse() {
    DepurarHoverTiles();
  }

  private void DepurarHoverTiles() {
    hoverTile.calcularDatos();
    String[] info = hoverTile.clasificarTile();

    if (info == null) {
      System.out.println("Tile ubicado en;" + hoverTile.gettilepX()
          + " " + hoverTile.gettileY()
          + " || Coordenadas Especificas: " + hoverTile.gettilepX()
          + " " + hoverTile.gettilepY()
          + " || Info: fuera del mapa");
      return;
    }

    hoverTile.calcularDatos();
    System.out.println("Tile ubicado en:" + hoverTile.gettileX()
        + " " + hoverTile.gettileY()
        + " || Coordenadas Especificas: " + hoverTile.gettilepX()
        + " " + hoverTile.gettilepY()
        + " || Nombre: " + info[0]
        + " || tile.ClassName: " + info[1]
        + " || Desc: " + info[2]);
  }

  private class HoverTile {
    private int pX;
    private int pY;
    private int col;
    private int row;

    public HoverTile() {

    }

    private void calcularDatos() {
      this.pX = mouseHandler.getX();
      this.pY = mouseHandler.getY();
      this.col = pX / GamePanel.TILE_SIZE;
      this.row = pY / GamePanel.TILE_SIZE;
    }

    private String[] clasificarTile() {
      Tile[][] piso = mapaManager.getPiso();

      if (col < 0 || col >= piso.length) {
        return null;
      }

      if (row < 0 || row >= piso[0].length) {
        return null;
      }

      Tile t = mapaManager.getTile(col, row);
      return t.getData();
    }

    private int gettilepX() {
      return pX;
    }

    private int gettilepY() {
      return pY;
    }

    private int gettileX() {
      return col;
    }

    private int gettileY() {
      return row;
    }

  }

}
