import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class KleeblattEins extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    while (!kara.onLeaf()) {
      
      if (kara.treeFront()) {
        kara.turnLeft();
        kara.move();
        kara.turnRight();
        kara.move();
        kara.move();
        kara.turnRight();
        kara.move();
        kara.turnLeft();
      }
      
      else {
        kara.move();
      }
    }
  }
}

        