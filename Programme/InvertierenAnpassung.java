import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class InvertierenAnpassung extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    while (!kara.treeFront()) {


        while (!kara.treeFront()) {                          //Bahn von links nach rechts
            if (kara.onLeaf()) {
                kara.removeLeaf();
            } else {           //else if unnötig?
                kara.putLeaf();
            }
            kara.move();
        }

        kara.turnRight();
        if (!kara.treeFront()) {
            if (kara.onLeaf()) {
                kara.removeLeaf();
            } else {           //else if unnötig?
                kara.putLeaf();
            }
            kara.move();
            if (kara.onLeaf()) {
                kara.removeLeaf();
            } else {           //else if unnötig?
                kara.putLeaf();
            }
            kara.turnRight();
            kara.move();
        }


        while (!kara.treeFront()) {                          //Bahn von rechts nach links
            if (kara.onLeaf()) {
                kara.removeLeaf();
            } else {           //else if unnötig?
                kara.putLeaf();
            }
            kara.move();
        }

        kara.turnLeft();
        if (!kara.treeFront()) {        
            if (kara.onLeaf()) {
                kara.removeLeaf();
            } else {           //else if unnötig?
                kara.putLeaf();
            }        
            kara.move();
            if (kara.onLeaf()) {
                kara.removeLeaf();
            } else {           //else if unnötig?
                kara.putLeaf();
            }
            kara.turnLeft();
            kara.move();
        }
    }
    if (kara.onLeaf()) {
        kara.removeLeaf();
    } else {           //else if unnötig?
            kara.putLeaf();
    }
  }
}                                                      // Abbruchbedingung ohne Variable möglich?

        