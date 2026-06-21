package app.func;

import app.tiles.*;
import app.structures.*;

public class MapaManager {

  private int mapCols = 40;
  private int mapRows = 20;
  private Tile[][] piso;
  private Structure[][] bloques;

  public MapaManager() {
    piso = new Tile[mapCols][mapRows];
    bloques = new Structure[mapCols][mapRows];

    for (int col = 0; col < mapCols; col++) {
      for (int row = 0; row < mapRows; row++) {
        piso[col][row] = new GrassTile();
      }
    }
  }

  public Tile getTile(int col, int row) {
    return piso[col][row];
  }

  public Tile[][] getPiso() {
    return piso;
  }

  public Structure getBloque(int col, int row) {
    return bloques[col][row];
  }

  public Structure[][] getBloques() {
    return bloques;
  }

}
