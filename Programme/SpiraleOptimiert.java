import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class SpiraleOptimiert extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:#
      for (int SZ=0; SZ<=world.getSizeX(); SZ++ ) {
        for (int SC=0; SC<=SZ; SC++ ) {
            kara.putLeaf();
            kara.move();
        }
        kara.turnRight();
    }
  }
}

        