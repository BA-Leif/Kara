import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class Invertieren extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    while (!kara.mushroomFront()) {

        while (!kara.treeFront()) {                          //Bahn von links nach rechts
            if (kara.onLeaf()) {
                kara.removeLeaf();
            } else if (!kara.onLeaf()) {           //else if unnötig?
                kara.putLeaf();
            }
            kara.move();
        }
        
        if (kara.onLeaf()) {
                kara.removeLeaf();
        } else if (!kara.onLeaf()) {           //else if unnötig?
                kara.putLeaf();
        }
        kara.turnRight();
        kara.move();
        if (kara.onLeaf()) {
            kara.removeLeaf();
        } else if (!kara.onLeaf()) {           //else if unnötig?
            kara.putLeaf();
        }
        kara.turnRight();
        kara.move();


        while (!kara.treeFront()) {                        //Bahn von rechts nach links
            if (kara.onLeaf()) {
                kara.removeLeaf();
            } else if (!kara.onLeaf()) {           //else if unnötig?
                kara.putLeaf();
            }
            kara.move();
        }
        
        if (kara.onLeaf()) {
                kara.removeLeaf();
        } else if (!kara.onLeaf()) {           //else if unnötig?
                kara.putLeaf();
        }
        kara.turnLeft();
        kara.move();
        if (kara.onLeaf()) {
            kara.removeLeaf();
        } else if (!kara.onLeaf()) {           //else if unnötig?
            kara.putLeaf();
        }
        kara.turnLeft();
        kara.move();
    }
  }
}                                                      // Abbruchbedingung ohne Variable möglich?

        