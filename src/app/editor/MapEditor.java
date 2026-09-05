package app.editor;

import app.tiles.*;
import app.structures.*;
import app.structures.blocks.*;

/*
 * MapEditor es un editor unificado para TILES y ESTRUCTURAS.
 *
 * En vez de tener dos clases separadas (TileEditor y StructureEditor)
 * con APIs distintas, MapEditor expone un único método "apply" que
 * decide qué hacer según el modo actual:
 *   - Mode.TILE      → edita el piso (matriz de Tile)
 *   - Mode.STRUCTURE → coloca/borra estructuras (matriz de Structure)
 *
 * El modo se controla desde fuera (KeyboardHandler / ActualizarEditor)
 * usando setMode(). Los tipos de brocha (brushType) también se separan
 * en dos canales:
 *   - tileBrushType       → para elegir el tipo de Tile
 *   - structureBrushType  → para elegir el tipo de Structure
 *
 * Esto te permite extender el editor más adelante (por ejemplo un
 * Mode.MODEL) sin duplicar lógica de input.
 */
public class MapEditor {

  // Enum sencillo para representar el modo actual del editor.
  public enum Mode {
    TILE,
    STRUCTURE
  }

  // Modo actual. Se setea desde el teclado.
  private Mode mode;

  // Brush para tiles (0-4, igual que antes en TileEditor)
  private int tileBrushType;

  // Brush para estructuras (0-2, igual que antes en StructureEditor)
  private int structureBrushType;

  // Modo borrador compartido: si es true, se borra en el canal activo
  private boolean eraserMode;

  public MapEditor() {
    // Por defecto arrancamos en modo TILE
    this.mode = Mode.TILE;
    this.tileBrushType = 0;
    this.structureBrushType = 0;
    this.eraserMode = false;
  }

  // ---------------------------------------------------------------------------
  // Setters y getters básicos para que otras clases controlen el editor
  // ---------------------------------------------------------------------------

  /*
   * Cambia el modo de trabajo del editor.
   *
   * Se llama desde KeyboardHandler cuando el usuario presiona una tecla
   * de tile (1-5) o de estructura (6-8).
   */
  public void setMode(Mode mode) {
    this.mode = mode;
  }

  public Mode getMode() {
    return mode;
  }

  /*
   * Brush para tiles. Se usa SOLO cuando mode == Mode.TILE.
   * KeyboardHandler mapea directamente las teclas 1-5 a valores de
   * tileBrushType.
   */
  public void setTileBrushType(int tileBrushType) {
    this.tileBrushType = tileBrushType;
  }

  /*
   * Brush para estructuras. Se usa SOLO cuando mode == Mode.STRUCTURE.
   * KeyboardHandler mapea directamente las teclas 6-8 a valores de
   * structureBrushType.
   */
  public void setStructureBrushType(int structureBrushType) {
    this.structureBrushType = structureBrushType;
  }

  /*
   * Activa o desactiva el modo borrador.
   * KeyboardHandler lo togglea con la tecla E.
   */
  public void setEraserMode(boolean eraserMode) {
    this.eraserMode = eraserMode;
  }

  public boolean getEraserMode() {
    return eraserMode;
  }

  // ---------------------------------------------------------------------------
  // Método principal: apply
  // ---------------------------------------------------------------------------

  /*
   * apply es el único método que el dispatcher (ActualizarEditor)
   * necesita llamar.
   *
   * Recibe las dos matrices:
   *   - piso      → Tile[][] (el piso del mapa)
   *   - bloques   → Structure[][] (las estructuras construidas)
   *
   * y coordina todo según el modo actual.
   */
  public void apply(Tile[][] piso, Structure[][] bloques, int col, int row) {

    // Validación básica de índices usando la matriz de piso como referencia.
    // Se asume que ambas matrices tienen el mismo tamaño.
    if (piso == null || bloques == null) {
      return;
    }

    if (col < 0 || col >= piso.length) {
      return;
    }

    if (row < 0 || row >= piso[0].length) {
      return;
    }

    // Dispatch según el modo actual
    if (mode == Mode.TILE) {
      applyTile(piso, col, row);
    } else if (mode == Mode.STRUCTURE) {
      applyStructure(piso, bloques, col, row);
    }
  }

  // ---------------------------------------------------------------------------
  // Implementación de TILE (fusion de TileEditor.apply)
  // ---------------------------------------------------------------------------

  private void applyTile(Tile[][] matriz, int col, int row) {

    if (eraserMode) {
      // Borrador en modo TILE: convertir el tile a VoidTile
      matriz[col][row] = new VoidTile();
      return;
    }

    Tile nuevoTile = null;

    switch (tileBrushType) {
      case 0:
        nuevoTile = new GrassTile();
        break;
      case 1:
        nuevoTile = new SandTile();
        break;
      case 2:
        nuevoTile = new StudTile();
        break;
      case 3:
        nuevoTile = new BrickFloorTile();
        break;
      case 4:
        // Reservado para futuros tiles
        // por ahora no hace nada
        return;
      default:
        // Si tileBrushType no coincide con nada, no tocar el tile
        return;
    }

    matriz[col][row] = nuevoTile;
  }

  // ---------------------------------------------------------------------------
  // Implementación de STRUCTURE (fusion de StructureEditor.ApicarCambios)
  // ---------------------------------------------------------------------------

  private void applyStructure(Tile[][] tiles, Structure[][] estructuras, int col, int row) {

    // Indices básicos ya se validaron en apply()

    if (eraserMode) {
      // Borrador simple: por ahora solo borra la celda clicada.
      // Más adelante puedes ampliar esto para borrar el área completa
      // usando gStructureWidth/gStructureHeight y gOriginCol/gOriginRow.
      estructuras[col][row] = null;
      return;
    }

    Tile suelo = tiles[col][row];
    if (suelo instanceof VoidTile) {
      // No construir sobre tiles nulos o inválidos
      return;
    }

    Structure nueva = null;

    switch (structureBrushType) {
      case 0:
        nueva = new Concrete1x1();
        break;
      case 1:
        nueva = new Concrete2x1();
        break;
      case 2:
        nueva = new Concrete2x2();
        break;
      default:
        // brush no válido → no construir
        return;
    }

    int w = nueva.gStructureWidth();
    int h = nueva.gStructureHeight();

    // Comprobación de límites: toda el área debe entrar en el mapa
    if (col + w > tiles.length) {
      return;
    }

    if (row + h > tiles[0].length) {
      return;
    }

    boolean canBuild = true;

    // Verificar que no haya estructuras ni VoidTile en el área
    for (int x = col; x < col + w; x++) {
      for (int y = row; y < row + h; y++) {
        Tile condicionTile = tiles[x][y];
        Structure condicionStruct = estructuras[x][y];

        if (condicionStruct != null) {
          canBuild = false;
          break;
        }

        if (condicionTile instanceof VoidTile) {
          canBuild = false;
          break;
        }
      }
      if (!canBuild) {
        break;
      }
    }

    if (!canBuild) {
      return;
    }

    // Si llegamos aquí, el área es válida para construir
    nueva.setOrigin(col, row);
    for (int x = col; x < col + w; x++) {
      for (int y = row; y < row + h; y++) {
        estructuras[x][y] = nueva;
      }
    }
  }
}
