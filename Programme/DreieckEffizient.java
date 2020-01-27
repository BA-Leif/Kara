import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class DreieckEffizient extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    boolean LookRight = true;
    for (int DreieckMax = 0; DreieckMax <= 20; DreieckMax = DreieckMax +2) {
      for (int DreieckCounter = 0; DreieckCounter <= DreieckMax; DreieckCounter++) {
        kara.putLeaf();
        kara.move();
      }
      LookRight = kurve(LookRight);
    }
  }


  public boolean kurve(boolean i) {
    if (i) {
      kara.turnRight();
      kara.move();
      kara.turnRight();
      return false;
    } else {
      kara.turnLeft();
      kara.move();
      kara.turnLeft();
      return true;
    }
  }

}

        