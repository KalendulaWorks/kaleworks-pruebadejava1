package app;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
  
  public GamePanel(){
    this.setPreferredSize(new Dimension(1280,720));
    this.setBackground(Color.DARK_GRAY);
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(Color.RED);
    g.drawRect(10,10,100,50);
  }

}
