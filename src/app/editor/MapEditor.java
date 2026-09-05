package app.editor;

import app.structures.*;
import app.structures.blocks.*;
import app.tiles.*;

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

  private int refWidth;
  private int refHeight;

  public MapEditor() {
    // Por defecto arrancamos en modo TILE
    this.mode = Mode.TILE;
    this.tileBrushType = 0;
    this.structureBrushType = 0;
    this.eraserMode = false;
    this.refWidth = 1;
    this.refHeight = 1;
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
  public void setBMode(Mode mode) {
    this.mode = mode;
  }

  public Mode getBMode() {
    return mode;
  }

  public int getBModeInt(){
   int BMode = 0;
   if (mode == Mode.TILE) {
    BMode = 1;
   } else {
    BMode = 2;
   }
   return BMode;
  }

  /*
   * Brush para tiles. Se usa SOLO cuando mode == Mode.TILE.
   * KeyboardHandler mapea directamente las teclas 1-5 a valores de
   * tileBrushType.
   */
  public void setTileBrushType(int tileBrushType) {
    this.tileBrushType = tileBrushType;
  }

  public void setRefSize(int w, int h){
    this.refWidth = w;
    this.refHeight = h;
  }

  public int gRefWidth(){
    return refWidth;
  }

  public int gRefHeigt(){
    return refHeight;
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


  private Structure elegirEstructura(){
    Structure selecS = null;
        switch (structureBrushType) {
      case 0:
        selecS = new Concrete1x1();
        setRefSize(selecS.gStructureWidth(),selecS.gStructureHeight());
        break;
      case 1:
        selecS = new Concrete2x1();
        setRefSize(selecS.gStructureWidth(),selecS.gStructureHeight());
        break;
      case 2:
        selecS = new Concrete2x2();
        setRefSize(selecS.gStructureWidth(),selecS.gStructureHeight());
        break;
      default:
        // brush no válido → no construir
        return null;
    }
    return selecS;
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
      Structure s = estructuras[col][row];

      if (s != null) {
      int oC = s.gOriginCol();
      int oR = s.gOriginRow();
      int sW = s.gStructureWidth();
      int sH = s.gStructureHeight();
      
      for (int x = oC; x < oC + sW; x++) {
        for (int y = oR; y < row + sH; y++) {
          estructuras[x][y] = null;
        }
      }
      return;
        } else {
          return;
      }
    }

    Tile suelo = tiles[col][row];
    if (suelo instanceof VoidTile) {
      // No construir sobre tiles nulos o inválidos
      return;
    }

    Structure nueva = elegirEstructura();

    switch (structureBrushType) {
      case 0:
        nueva = new Concrete1x1();
        setRefSize(nueva.gStructureWidth(),nueva.gStructureHeight());
        break;
      case 1:
        nueva = new Concrete2x1();
        setRefSize(nueva.gStructureWidth(),nueva.gStructureHeight());
        break;
      case 2:
        nueva = new Concrete2x2();
        setRefSize(nueva.gStructureWidth(),nueva.gStructureHeight());
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
