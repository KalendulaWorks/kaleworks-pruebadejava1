package app;

import javax.swing.*;
import app.func.*;
import app.util.*;

public class AppWindow extends JFrame {
  public AppWindow() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel etiqueta = new JLabel("ola sebas");
    GamePanel panel = new GamePanel();
    this.add(panel);
    this.pack();
    this.setVisible(true);

    EditorBootstrap.iniciarEditor(panel);
  }
}
