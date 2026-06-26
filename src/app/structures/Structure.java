package app.structures;

import java.awt.*;

public abstract class Structure {
  protected String[] structureInfo;

  protected boolean canCollide;
  protected int widthInTiles;
  protected int heightInTiles;

  public Structure(boolean collide, int widthInTiles, int heightInTiles) {
    this.canCollide = collide;
    this.widthInTiles = widthInTiles;
    this.heightInTiles = heightInTiles;
  }

  protected void infoDeStructure(String nombre, String nombreT, String desc, String collide, String intr) {
    this.structureInfo = new String[] { nombre, nombreT, desc, collide, intr };
  }

  public String[] gStructureData() {
    return structureInfo;
  }

  public int gStructureWidth() {
    return widthInTiles;
  }

  public int gStructureHeight() {
    return heightInTiles;
  }

  public boolean gCollide() {
    return canCollide;
  }

  public abstract void draw(Graphics g, int x, int y);

}
