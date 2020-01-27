import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class Slalom extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:                      //Idee: Kara muss immer rechts und links wechseln, AUCH WENN sie den Baum am Ende umrundet
    while (!kara.onLeaf()) {
        if (kara.treeLeft()) {
            kara.move();
            kara.turnLeft();
            kara.move();
            kara.move();
            kara.turnLeft();
            kara.move();
            if (!(kara.treeLeft() && kara.treeRight()))  {
                kara.move();
                kara.turnLeft();
                kara.move();
                kara.move();
                kara.turnLeft();
                kara.move();
            }
        }
        if (kara.treeRight()) {
            kara.move();
            kara.turnRight();
            kara.move();
            kara.move();
            kara.turnRight();
            kara.move();
            if (!(kara.treeLeft() && kara.treeRight()))  {
                kara.move();
                kara.turnRight();
                kara.move();
                kara.move();
                kara.turnRight();
                kara.move();
            }
        }
    }
  }
}

        