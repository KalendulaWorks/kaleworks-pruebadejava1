package app.util;

import app.GamePanel;
import app.editor.*;
import app.func.*;
import app.util.*;

public class EditorBootstrap {

  public static void iniciarEditor(GamePanel panel) {
    ActualizarEditor actualizarEditor = new ActualizarEditor(
        panel.getMapaManager(),
        panel.getTileEditor(),
        panel.getMouseHandler(),
        panel.getMouseHoverHandler(),
        panel.getStructureEditor());
    new GameLoop(actualizarEditor, panel);
  }

}
