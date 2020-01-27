import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class DreieckKurz extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    for (int DreieckZahl = 0; DreieckZahl <= 16; DreieckZahl = DreieckZahl +2) {        //links und rechts wäre eleganter mit Variablen zu lösen? !
      for (int DreieckCounter = 0; DreieckCounter <= DreieckZahl; DreieckCounter++) {
        kara.putLeaf();
        kara.move();            
      }
      kara.turnRight();
      kara.turnRight();
      for (int DreieckCounter = 0; DreieckCounter <= DreieckZahl +1; DreieckCounter++) {
        kara.move();
      }
      kara.turnLeft();
      kara.move();
      kara.turnLeft();
    }
  }
}

        