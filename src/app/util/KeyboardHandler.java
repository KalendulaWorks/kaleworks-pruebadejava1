package app.util;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import app.tiles.*;
import app.editor.*;

public class KeyboardHandler implements KeyListener {
  private TileEditor tileEditor;
  private StructureEditor structureEditor;
  private boolean eraserMode;

  public KeyboardHandler(TileEditor tileEditor, StructureEditor structureEditor) {
    this.tileEditor = tileEditor;
    this.structureEditor = structureEditor;
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

      case KeyEvent.VK_4:
        tileEditor.setBrushType(3);
        tileEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_5:
        tileEditor.setBrushType(4);
        tileEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_6:
        structureEditor.setBrushType(0);
        structureEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_7:
        structureEditor.setBrushType(1);
        structureEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_8:
        structureEditor.setBrushType(2);
        structureEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_E:
        eraserMode = !eraserMode;
        tileEditor.setEraserMode(eraserMode);
        structureEditor.setEraserMode(eraserMode);
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
