package app.util;

import app.GamePanel;
import app.func.*;

public class EditorBootstrap {
  public static void iniciarEditor(GamePanel panel) {
    ActualizarEditor actualizarEditor = new ActualizarEditor(
        panel.getMapaManager(),
        panel.getTileEditor(),
        panel.getMouseHandler());
    new GameLoop(actualizarEditor, panel);
  }
}
