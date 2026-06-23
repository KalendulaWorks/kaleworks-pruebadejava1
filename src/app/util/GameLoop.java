package app.util;

import app.func.*;
import app.GamePanel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameLoop {
  private ActualizarEditor actualizarEditor;
  private GamePanel gamePanel;
  private Timer timer;

  public GameLoop(ActualizarEditor actualizarEditor, GamePanel gamePanel, MouseHoverHandler mouseHoverHandler) {
    this.actualizarEditor = actualizarEditor;
    this.gamePanel = gamePanel;

    int delay = 33;

    timer = new Timer(delay, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        actualizarEditor.updateEditor();
        gamePanel.repaint();
      }
    });
    timer.start();
  }

}
