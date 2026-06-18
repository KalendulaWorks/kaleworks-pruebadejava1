package app.util;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MouseHandler implements MouseListener {
  private int mouseX;
  private int mouseY;
  private boolean leftPressed;
  private boolean rightPressed;

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

  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    mouseX = e.getX();
    mouseY = e.getY();

    if (e.getButton() == MouseEvent.BUTTON1) {
      leftPressed = true;
    } else if (e.getButton() == MouseEvent.BUTTON3) {
      rightPressed = true;
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
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

}
