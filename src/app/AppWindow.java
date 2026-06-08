package app;

import javax.swing.*;

public class AppWindow extends JFrame{
  public AppWindow(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel etiqueta = new JLabel("ola sebas");
    this.getContentPane().add(etiqueta);
    this.pack();
    this.setVisible(true);
  }
}
