package app.util;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class MouseHandler implements MouseListener, MouseMotionListener {
  private int mouseX;
  private int mouseY;
  private boolean leftPressed;
  private boolean rightPressed;
  private boolean MouseHold;
  private boolean MouseSingle;
  private int clickStartX;
  private int clickStartY;

  public int getX() {
    return mouseX;
  }

  public int getY() {
    return mouseY;
  }

  public boolean getLeftM() {
    return leftPressed;
  }

  public boolean getRightM() {
    return rightPressed;
  }

  public boolean getMouseHold() {
    return MouseHold;
  }

  public boolean getMouseSingle() {
    return MouseSingle;
  }

  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    mouseX = e.getX();
    mouseY = e.getY();
    clickStartX = e.getX();
    clickStartY = e.getY();
    MouseSingle = true;
    MouseHold = false;
    if (e.getButton() == MouseEvent.BUTTON1) {
      leftPressed = true;
    } else if (e.getButton() == MouseEvent.BUTTON3) {
      rightPressed = true;
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();

    int startCol = clickStartX / 32;
    int startRow = clickStartY / 32;
    int endCol = mouseX / 32;
    int endRow = mouseY / 32;

    if (endCol != startCol || endRow != startRow) {
      // hubo desplazamiento a otro tile → drag
      MouseHold = true;
      MouseSingle = false;
      System.out.println("Soltar tras DRAG");
    } else {
      // se mantuvo en el mismo tile → click simple
      MouseHold = false;
      MouseSingle = true;
      System.out.println("Click Simple");
    }

    if (e.getButton() == MouseEvent.BUTTON1) {
      leftPressed = false;
    } else if (e.getButton() == MouseEvent.BUTTON3) {
      rightPressed = false;
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  public void mouseMoved(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();
  }

  public void mouseDragged(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();

    int startCol = clickStartX / 32;
    int startRow = clickStartY / 32;
    int currCol = mouseX / 32;
    int currRow = mouseY / 32;

    if (currCol != startCol || currRow != startRow) {
      MouseHold = true;
      MouseSingle = false;
      System.out.println("Modo Drag: " + MouseHold);
    }
  }
}
