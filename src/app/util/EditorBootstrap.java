package app.util;

import app.GamePanel;
import app.editor.MapEditor;
import app.func.ActualizarEditor;
import app.util.GameLoop;

/*
 * EditorBootstrap conecta GamePanel con el sistema de edición.
 *
 * Responsabilidades:
 *   - Obtener del panel los componentes necesarios (MapaManager, MouseHandler,
 *     MouseHoverHandler, MapEditor).
 *   - Crear una instancia de ActualizarEditor usando esos componentes.
 *   - Crear el GameLoop, que se encarga de llamar updateEditor() y repaint()
 *     en cada "tick".
 */
public class EditorBootstrap {

  public static void iniciarEditor(GamePanel panel) {
    // Obtener referencias desde el GamePanel
    MapEditor mapEditor = panel.getMapEditor();

    ActualizarEditor actualizarEditor = new ActualizarEditor(
        panel.getMapaManager(),
        panel.getMouseHandler(),
        panel.getMouseHoverHandler(),
        mapEditor);

    // Iniciar el bucle de juego/edicion
    new GameLoop(actualizarEditor, panel);
  }

}
