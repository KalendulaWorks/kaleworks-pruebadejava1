package app.util;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import app.tiles.*;
import app.editor.*;

public class KeyboardHandler implements KeyListener {
  private TileEditor tileEditor;
  private boolean eraserMode;

  public KeyboardHandler(TileEditor tileEditor) {
    this.tileEditor = tileEditor;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    int code = e.getKeyCode();

    switch (code) {
      case KeyEvent.VK_1:
        tileEditor.setBrushType(0);
        tileEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_2:
        tileEditor.setBrushType(1);
        tileEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_3:
        tileEditor.setBrushType(2);
        tileEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_E:
        eraserMode = !eraserMode;
        tileEditor.setEraserMode(eraserMode);
        break;
      default:
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

  }
}
