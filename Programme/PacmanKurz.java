import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class PacmanKurz extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    while (!kara.treeFront()) {
      kara.removeLeaf();
    //  kara.turnLeft();                    //wäre schöner, könnte man die noch in den do-Befehl einbinden
      kara.move();                        //
      while (!kara.onLeaf()) {                                //do-while macht es weder kürzer noch länger
          kara.turnLeft();
          kara.turnLeft();
          kara.move();
          kara.turnLeft();
          kara.move();
      } 
    }
    kara.removeLeaf();
  }
}

        