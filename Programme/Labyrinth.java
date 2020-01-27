import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class Labyrinth extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:

      // IDEE: Kara läuft immer an der rechten Seite lang.... gibt jedoch Probleme bei einer "MauerInsel"
      
      // alternative Idee: In Variable speichern, in welche Richtung Kara guckt. Dann Kara immer so lange laufen lassen, bis ein Stall links fehlt, wenn sie nach rechts guckt.
      //                Oder bis ein Stamm rechts fehlt, wenn sie nach links guckt.

    while (!kara.onLeaf()) {
        if (!kara.treeFront() && kara.treeRight()) {
             kara.move();
        }
        else if (kara.treeFront() && !kara.treeRight()) {
            kara.turnRight();
            kara.move();
            kara.move();
            kara.turnRight();
            if (!kara.treeFront() && !kara.onLeaf()) {
                kara.move();
            }
        }
        else if (!kara.treeFront() && !kara.treeRight()) {
            kara.turnRight();
            kara.move();
            kara.move();
            kara.turnRight();
            if (!kara.treeFront() && !kara.onLeaf()) {
                kara.move();
            }
        }
        else if (kara.treeFront() && kara.treeRight()) {
            kara.turnRight();
            kara.turnRight();
        }
    }
  }
}

        