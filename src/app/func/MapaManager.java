package app.func;

import app.tiles.*;

public class MapaManager {

  private int mapCols = 40;
  private int mapRows = 20;
  private Tile[][] cuadros;

  public MapaManager() {
    cuadros = new Tile[mapCols][mapRows];

    for (int col = 0; col < mapCols; col++) {
      for (int row = 0; row < mapRows; row++) {
        cuadros[col][row] = new GrassTile(1, 1);
      }
    }
  }

  public Tile geTile(int col, int row) {
    return cuadros[col][row];
  }

  public Tile[][] getCuadros() {
    return cuadros;
  }

}
