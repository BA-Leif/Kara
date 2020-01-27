import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class SlalomKurz extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:                     
    while (true) {       //auch, wenn man das nicht machen sollte

        do {
            kara.move();
            kara.turnLeft();
            kara.move();
        } while (!(kara.treeRight() && kara.treeLeft()));
            
        do {
            kara.move();
            kara.turnRight();
            kara.move();
        } while (!(kara.treeRight() && kara.treeLeft()));
    }
  }
}

        