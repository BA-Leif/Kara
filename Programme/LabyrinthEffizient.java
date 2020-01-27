import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class LabyrinthEffizient extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    boolean LookRight = true;
    while (!kara.onLeaf()) {


      if ((LookRight &&  (!kara.treeLeft() == LookRight))    ||    (!LookRight &&  (!kara.treeRight() == !LookRight))) {
        if (LookRight) {
          kara.turnLeft();
          kara.move();
          kara.move();
          kara.turnRight();
        }else{
          kara.turnRight();
          kara.move();
          kara.move();
          kara.turnLeft();
        }
        if (!kara.treeFront() && !kara.onLeaf()) {
          kara.move();
        }
      }else{
        if (!kara.treeFront()) {
          kara.move();
        }else{
          kara.turnLeft();
          kara.turnLeft();
          LookRight = !LookRight;
        }
      }
    }
  }
}

        