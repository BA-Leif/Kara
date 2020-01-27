import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class PacMan1 extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    kara.removeLeaf();                  //Startbedingung


    while (!kara.treeFront()) {
        kara.move();                    
        if (!kara.onLeaf()) {           //Feld vor Kara Untersuchen
            kara.turnRight();           //zum ehemalig linken Feld gehen
            kara.turnRight();
            kara.move();
            kara.turnRight();
            kara.move();
        } 
        if (!kara.onLeaf()) {          //ehemalig links Feld untersuchen             
            kara.turnRight();          //zum ehemals rehcten Feld gehen
            kara.turnRight();
            kara.move();
            kara.move();
        }
        kara.removeLeaf();                 //Klee nehmen
    }


  }
}

        