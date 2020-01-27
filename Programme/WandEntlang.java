import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class WandEntlang extends JavaKaraProgram {

  // hier k�nnen Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    if (!kara.treeFront()) {
        kara.move();                                              //sehr unsch�n, aber mir f�llt nichts besseres f�r die zweite Welt ein  
    }
    while (!kara.onLeaf()) {

        if (!kara.treeFront()  &&  (!kara.treeRight() && !kara.treeLeft())) {
            kara.turnRight();
            kara.move();
        }
        if (!kara.treeFront()  &&  !(!kara.treeRight() && !kara.treeLeft())) {
            kara.move();
        }
        if (kara.treeFront() && kara.treeRight()) {
            kara.turnLeft();
//            kara.move();
        } else if (kara.treeFront() && kara.treeLeft()) {        //else mit Bedingung m�glich? JA, mit else if
            kara.turnLeft();
//            kara.move();
        }
    }
  }
}

        