package app.func;

import app.tiles.*;
import app.structures.*;
import app.util.*;
import app.editor.*;
import app.GamePanel;

public class ActualizarEditor {
  private MouseHandler mouseHandler;
  private TileEditor editorDeCuadros;
  private MapaManager mapaManager;
  private MouseHoverHandler mouseHoverHandler;
  private StructureEditor structureEditor;

  /*
   * modoEstructuras es el "switch" central del editor.
   *
   * false (default) → el editor trabaja sobre el piso (TileEditor).
   * true            → el editor trabaja sobre estructuras (StructureEditor).
   *
   * Se cambia desde afuera con setModoEstructuras(boolean).
   * KeyboardHandler es quien lo llama: cuando el usuario presiona
   * una tecla de estructura (6, 7, 8...) activa modoEstructuras = true,
   * y cuando presiona una tecla de tile (1, 2, 3, 4...) lo desactiva.
   */
  private boolean modoEstructuras;

  public ActualizarEditor(MapaManager mapaManager, TileEditor editorDeCuadros, MouseHandler mouseHandler,
      MouseHoverHandler mouseHoverHandler, StructureEditor structureEditor) {
    this.mapaManager = mapaManager;
    this.editorDeCuadros = editorDeCuadros;
    this.mouseHandler = mouseHandler;
    this.mouseHoverHandler = mouseHoverHandler;
    this.structureEditor = structureEditor;

    // Por defecto arrancamos editando el piso
    this.modoEstructuras = false;
  }

  /*
   * Setter público para que KeyboardHandler pueda cambiar el modo.
   * Cuando el usuario elige un brush de tile  → setModoEstructuras(false)
   * Cuando el usuario elige un brush de estructura → setModoEstructuras(true)
   */
  public void setModoEstructuras(boolean modoEstructuras) {
    this.modoEstructuras = modoEstructuras;
  }

  /*
   * Getter por si alguna otra clase necesita saber en qué modo estamos
   * (por ejemplo la GUI para mostrar un indicador al usuario).
   */
  public boolean getModoEstructuras() {
    return modoEstructuras;
  }

  /*
   * updateEditor() se llama cada frame desde el GameLoop.
   *
   * El flujo es:
   *   1. Calcular col y row a partir de la posición del mouse.
   *   2. Decidir qué editor usar dependiendo de modoEstructuras.
   *   3. Ejecutar la acción de pincel (hold) o click simple (single).
   *   4. Actualizar el hover para el debug de tile.
   */
  public void updateEditor() {
    despacharAccion();
    mouseHoverHandler.ActualizarMouse();
  }

  /*
   * despacharAccion() es el método central que reemplaza los tres métodos
   * separados que existían antes (editarCuadroPiso, editarCuadroEstructura,
   * aplicarClickSimple).
   *
   * Primero detecta si hay alguna acción de mouse activa:
   *   - MouseHold  → pincel continuo mientras se mantiene el botón.
   *   - MouseSingle → click puntual, se consume una sola vez.
   *
   * Luego hace el switch sobre modoEstructuras:
   *   - false → llama a TileEditor.apply()
   *   - true  → llama a StructureEditor.ApicarCambios()
   *
   * Separar la decisión "¿hay acción?" de la decisión "¿qué editor?"
   * hace que sea fácil agregar más modos en el futuro (por ejemplo,
   * un modo de modelos) sin duplicar la lógica de input.
   */
  private void despacharAccion() {

    // --- Pincel continuo: activo mientras el botón izquierdo está presionado ---
    boolean pincelActivo = mouseHandler.getLeftM() && mouseHandler.getMouseHold();

    // --- Click simple: solo se dispara una vez por click ---
    boolean clickSimple = mouseHandler.getMouseSingle();

    // Si no hay ninguna acción de mouse, no hay nada que hacer
    if (!pincelActivo && !clickSimple) {
      return;
    }

    // Calcular la celda del mapa donde está el cursor
    int col = mouseHandler.getX() / GamePanel.TILE_SIZE;
    int row = mouseHandler.getY() / GamePanel.TILE_SIZE;

    /*
     * Switch principal: decide qué editor recibe la acción.
     *
     * Se usa el booleano modoEstructuras como condición:
     *   false → modo TILE  (edición del piso)
     *   true  → modo ESTRUCTURA
     *
     * Nota: los structures solo responden al pincel continuo (MouseHold)
     * porque colocar una estructura 2x2 con click simple podría dar lugar
     * a colocaciones accidentales. Si en el futuro quieres habilitar click
     * simple para estructuras, solo descomenta la línea marcada abajo.
     */
    if (!modoEstructuras) {
      // ── MODO TILE ──────────────────────────────────────────────────────────
      // Tanto el pincel como el click simple editan el piso.
      // El click simple además se "consume" para que no se repita el próximo frame.
      if (pincelActivo || clickSimple) {
        editorDeCuadros.apply(mapaManager.getPiso(), col, row);
      }
      if (clickSimple) {
        mouseHandler.clearMouseSingle(); // consumir el click
      }

    } else {
      // ── MODO ESTRUCTURA ────────────────────────────────────────────────────
      // Solo el pincel continuo coloca estructuras.
      // (descomenta la siguiente línea si quieres habilitar click simple también)
      // if (pincelActivo || clickSimple) {
      if (pincelActivo) {
        structureEditor.ApicarCambios(mapaManager.getBloques(), mapaManager.getPiso(), col, row);
      }
      if (clickSimple) {
        mouseHandler.clearMouseSingle(); // consumir el click aunque no haga nada aquí
      }
    }
  }
}
