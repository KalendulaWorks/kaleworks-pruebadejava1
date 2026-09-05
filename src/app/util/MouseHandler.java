package app.util;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

/*
 * MouseHandler maneja todos los eventos del mouse.
 *
 * Campos:
 *   mouseX, mouseY  → posicion actual del cursor en pantalla (pixeles).
 *   leftPressed     → true SOLO mientras el boton izquierdo esta fisicamente
 *                      presionado. Se pone en false cuando lo sueltas.
 *   rightPressed    → igual para el boton derecho.
 *   dragging        → true mientras el usuario arrastra con el boton
 *                      presionado Y el cursor se ha movido a otro tile.
 *   MouseSingle     → true cuando el usuario hizo click y lo soltó dentro
 *                      del mismo tile (sin arrastrar).
 *                      Se "consume" con clearMouseSingle() despues de procesar.
 *   clickStartX/Y   → posicion donde se presiono el boton por primera vez.
 *
 * El GameLoop llama cada tick a ActualizarEditor, que consulta:
 *   - getMouseHold()   para decidir si continuar pintando (pincel).
 *   - getMouseSingle() para decidir si aplicar un click puntual.
 *
 * La distincion entre dragging y MouseSingle es importante:
 *   - dragging es true SOLO mientras el boton sigue presionado y el cursor
 *     se ha desplazado. En cuanto sueltas, dragging vuelve a false.
 *   - MouseSingle solo se activa en mouseReleased si NO hubo desplazamiento.
 */
public class MouseHandler implements MouseListener, MouseMotionListener {
  private int mouseX;
  private int mouseY;
  private boolean leftPressed;
  private boolean rightPressed;
  private boolean dragging;     // reemplaza MouseHold
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

  /*
   * getMouseHold devuelve true SOLO si el boton sigue presionado
   * y el cursor se ha desplazado a otro tile (drag activo).
   * En cuanto el usuario suelta el boton, dragging vuelve a false
   * y el editor deja de pintar automaticamente.
   */
  public boolean getMouseHold() {
    return dragging;
  }

  public boolean getMouseSingle() {
    return MouseSingle;
  }

  /*
   * mousePressed: se llama al instante que el usuario baja el boton.
   * - Guardamos donde empezo el click.
   * - Armamos leftPressed o rightPressed segun el boton.
   * - dragging parte en false; solo lo activamos si hay desplazamiento.
   * - MouseSingle parte en false; lo activamos en mouseReleased si no hubo drag.
   */
  public void mousePressed(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();
    clickStartX = e.getX();
    clickStartY = e.getY();
    MouseSingle = false;
    dragging = false;
    if (e.getButton() == MouseEvent.BUTTON1) {
      leftPressed = true;
    } else if (e.getButton() == MouseEvent.BUTTON3) {
      rightPressed = true;
    }
  }

  /*
   * mouseReleased: se llama cuando el usuario suelta el boton.
   * - Si hubo desplazamiento a otro tile durante el drag, el click fue
   *   un "pincel"; no generamos MouseSingle.
   * - Si NO se movio de tile, fue un click simple; activamos MouseSingle.
   * - En cualquier caso, dragging vuelve a false porque el boton ya
   *   no esta presionado. Esto evita que el editor siga pintando
   *   despues de soltar.
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();

    int startCol = clickStartX / 32;
    int startRow = clickStartY / 32;
    int endCol = mouseX / 32;
    int endRow = mouseY / 32;

    if (endCol != startCol || endRow != startRow) {
      // Hubo desplazamiento: fue un drag, no un click simple
      MouseSingle = false;
    } else {
      // Se mantuvo en el mismo tile: click simple
      MouseSingle = true;
    }

    // IMPORTANTE: apagar dragging al soltar para que el editor
    // no siga pintando en el proximo tick del GameLoop
    dragging = false;

    if (e.getButton() == MouseEvent.BUTTON1) {
      leftPressed = false;
    } else if (e.getButton() == MouseEvent.BUTTON3) {
      rightPressed = false;
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // sin uso: usamos mousePressed + mouseReleased para mas control
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // sin uso
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // sin uso
  }

  /*
   * mouseMoved: se llama cuando el mouse se mueve sin boton presionado.
   * Solo actualizamos la posicion para el hover de debug.
   */
  public void mouseMoved(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();
  }

  /*
   * mouseDragged: se llama mientras el usuario mueve el mouse CON
   * el boton presionado.
   * - Actualizamos posicion del cursor.
   * - Si el cursor entro a un tile diferente al origen del click,
   *   activamos dragging = true, que es lo que el editor lee como
   *   "modo pincel activo".
   */
  public void mouseDragged(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();

    int startCol = clickStartX / 32;
    int startRow = clickStartY / 32;
    int currCol = mouseX / 32;
    int currRow = mouseY / 32;

    if (currCol != startCol || currRow != startRow) {
      dragging = true;
      MouseSingle = false;
    }
  }

  /*
   * clearMouseSingle: limpia la bandera de click simple despues de
   * que el editor la proceso, para que no se repita en el proximo tick.
   */
  public void clearMouseSingle() {
    MouseSingle = false;
  }
}
