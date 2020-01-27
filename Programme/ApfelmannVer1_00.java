import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class ApfelmannVer1_00 extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    tools.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
    world.clearAll();
    world.setSize(20, 20);

    float[] coordinateStart = new float[2];
    float[] coordinateTarget = new float[2];
    float[] OriginFloat = new float[2];
    boolean inArea = true;

    for (int xOrigin = 0; xOrigin < world.getSizeX(); xOrigin++) {
      for (int yOrigin = 0; yOrigin < world.getSizeY(); yOrigin++) {
        OriginFloat[0] = (xOrigin - (world.getSizeX()/2)) / (world.getSizeX()/4);
        OriginFloat[1] = (yOrigin - (world.getSizeY()/2)) / (world.getSizeY()/4);
        coordinateStart[0] = OriginFloat[0];
        coordinateStart[1] = OriginFloat[1];
tools.println(Integer.toString(xOrigin) + "_________" + Integer.toString(yOrigin) );
tools.println(Float.toString(coordinateStart[0]) + "_________" + Float.toString(coordinateStart[1]) );

        for (int i = 0; (i <= 99 && inArea) ; i++) {
          coordinateTarget[0] = ((coordinateStart[0]*coordinateStart[0]) - (coordinateStart[0]*coordinateStart[1])) + OriginFloat[0];
          coordinateTarget[1] = (2 * coordinateStart[0] * coordinateStart[1]) + OriginFloat[1];

          if (Math.abs(coordinateTarget[0]) <= 2    &&    Math.abs(coordinateTarget[1]) <= 2) {
            coordinateStart[0] = coordinateTarget[0];
            coordinateStart[1] = coordinateTarget[1];
            if (i == 99) {
              world.setMushroom(xOrigin, yOrigin, true);
            }
          } else {
            inArea = false;
            world.setLeaf(xOrigin, yOrigin, true);

          }
        }
        inArea = true;
      }
    }
  }
}

        