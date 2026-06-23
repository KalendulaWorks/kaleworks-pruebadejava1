package app.structures;

import java.awt.*;

public abstract class Structure {
  protected String[] tileInfo;

  protected boolean collide;

  public Structure(boolean collide) {
    this.collide = collide;
  }

  protected void infoDeStructure(String nombre, String nombreT, String desc, String collide, String intr) {
    this.tileInfo = new String[] { nombre, nombreT, desc, collide, intr };
  }

  public String[] gStructureData() {
    return tileInfo;
  }

  public abstract void draw(Graphics g, int x, int y);
}
