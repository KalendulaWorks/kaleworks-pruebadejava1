package app.editor;

import app.structures.*;
import app.structures.blocks.*;
import app.tiles.*;

public class StructureEditor {
  private int brushType;
  private boolean eraserMode;

  public void setBrushType(int brushType) {
    this.brushType = brushType;
  }

  public void setEraserMode(boolean eraserMode) {
    this.eraserMode = eraserMode;
  }

  public void ApicarCambios(Structure[][] estructuras, Tile[][] tiles, int col, int row) {

    boolean canBuild;

    if (col < 0 || col >= estructuras.length) {
      return;
    }

    if (row < 0 || row >= estructuras[0].length) {
      return;
    }

    if (eraserMode) {
      estructuras[col][row] = null;
      return;
    }

    Tile suelo = tiles[col][row];
    if (suelo instanceof VoidTile) {
      return;
    }

    Structure nueva = null;

    if (col < 0 || row < 0) {
      return;
    }

    switch (brushType) {
      case 0:
        nueva = new Concrete1x1();
        break;
      case 1:
        nueva = new Concrete2x2();
        break;
      default:
        return;
    }

    int w = nueva.gStructureWidth();
    int h = nueva.gStructureHeight();

    if (col + w > tiles.length) {
      return;
    }

    if (row + h > tiles[0].length) {
      return;
    }

    canBuild = true;

    for (int x = col; x < col + w; x++) {
      for (int y = row; y < row + h; y++) {
        Tile condicion = tiles[x][y];
        Structure condicion2 = estructuras[x][y];

        if (condicion2 != null) {
          canBuild = false;
          break;
        }

        if (condicion instanceof VoidTile) {
          canBuild = false;
          break;
        }
      }
      if (!canBuild) {
        break;
      }
    }

    if (canBuild) {
      nueva.setOrigin(col, row); // <-- esto sí es válido
      for (int x = col; x < col + w; x++) {
        for (int y = row; y < row + h; y++) {
          estructuras[x][y] = nueva;
        }
      }
    }
  }
}
