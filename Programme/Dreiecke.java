import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class Dreiecke extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    int DreieckZahl = 0;
    int DreieckCounter = 0;

    while (DreieckZahl <= 16) {        //links und rechts wäre eleganter mit Variablen zu lösen? !
        while (DreieckCounter <= DreieckZahl) {
            DreieckCounter = DreieckCounter + 1;
            kara.putLeaf();
            kara.move();
            
        }
        DreieckCounter = DreieckCounter + 1;
        kara.turnRight();
        kara.turnRight();
        while (!(DreieckCounter==0)) {
            kara.move();
            DreieckCounter = DreieckCounter - 1;
        }
        kara.turnLeft();
        kara.move();
        kara.turnLeft();
        DreieckZahl = DreieckZahl + 2;
    }
  }
}

        