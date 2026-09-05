package app.util;

import app.GamePanel;
import app.func.MapaManager;
import app.structures.Structure;
import app.tiles.Tile;

public class MouseHoverHandler {
  private MouseHandler mouseHandler;
  private GamePanel panel;
  private HoverTile hoverTile;
  private HoverStructure hoverStructure;
  private MapaManager mapaManager;

  public static boolean DEBUG = true;

  public MouseHoverHandler(GamePanel panel) {
    this.panel = panel;
    this.mouseHandler = panel.getMouseHandler();
    this.hoverTile = new HoverTile();
    this.hoverStructure = new HoverStructure();
    this.mapaManager = panel.getMapaManager();
  }

  public void ActualizarMouse() {
    hoverTile.calcularDatos();
    hoverStructure.calcularDatos();
    if (DEBUG) {
      DepurarHoverTiles();
      DepurarHoverStructures();
    }

  }

  private void DepurarHoverTiles() {
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

  private void DepurarHoverStructures() {
    String[] info = hoverStructure.clasificarStructura();

    if (info == null) {
      System.out.println("Estructura vacia en;" + hoverTile.gettilepX()
          + " " + hoverTile.gettileY()
          + " || Coordenadas Especificas: " + hoverTile.gettilepX()
          + " " + hoverTile.gettilepY()
          + " || Info: Slot libre de estructuras");
      return;
    }

    hoverStructure.calcularDatos();
    System.out.println("Estructura ubicada en:" + hoverStructure.getstrucX()
        + " " + hoverStructure.getstrucY()
        + " || Coordenadas Especificas: " + hoverStructure.getstrucpX()
        + " " + hoverStructure.getstrucpY()
        + " || Nombre: " + info[0]
        + " || tile.ClassName: " + info[1]
        + " || Desc: " + info[2]);
  }

  public int gTileCol() {
    return hoverTile.gettileX();
  }

  public int gTileRow() {
    return hoverTile.gettileY();
  }

  public int gTileHoverX() {
    return hoverTile.gettilepX();
  }

  public int gTileHoverY() {
    return hoverTile.gettilepY();
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
      return t.gTileData();
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

  private class HoverStructure {
    private int pX;
    private int pY;
    private int col;
    private int row;

    public HoverStructure() {

    }

    private void calcularDatos() {
      this.pX = mouseHandler.getX();
      this.pY = mouseHandler.getY();
      this.col = pX / GamePanel.TILE_SIZE;
      this.row = pY / GamePanel.TILE_SIZE;
    }

    private String[] clasificarStructura() {
      Structure[][] bloques = mapaManager.getBloques();

      if (col < 0 || col >= bloques.length) {
        return null;
      }

      if (row < 0 || row >= bloques[0].length) {
        return null;
      }

      Structure s = mapaManager.getBloque(col, row);
      
      if (s != null) {
      return s.gStructureData(); 
      } else {
      return null; 
      }

    }

    private int getstrucpX() {
      return pX;
    }

    private int getstrucpY() {
      return pY;
    }

    private int getstrucX() {
      return col;
    }

    private int getstrucY() {
      return row;
    }

  }
}
