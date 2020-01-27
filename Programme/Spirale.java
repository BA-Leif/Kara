import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class Spirale extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    int SpiralZahl = 0;
    int SpiralCounter = 0;

    while (SpiralZahl<=18 ) {

        while (SpiralCounter <= SpiralZahl) {
            SpiralCounter = SpiralCounter + 1;
            kara.putLeaf();
            kara.move();
        }

        kara.turnRight();
        SpiralCounter = 0;
        SpiralZahl = SpiralZahl + 1;
    }
  }
}

        