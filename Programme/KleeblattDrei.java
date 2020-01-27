import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class KleeblattDrei extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    while (!kara.onLeaf()) {
      


      if (!kara.treeFront()) {
        kara.move();
      }
// Schleife mit zweimal ELSE nicht möglich???????????????????????????????????????
      else {
          if (!kara.treeLeft()) {
              kara.turnLeft();      
              kara.move();
          }
          else {
              if (!kara.treeRight()) {
                 kara.turnRight();
                 kara.move();
              }
              else {
                  if (!(kara.treeFront() && kara.treeLeft() && kara.treeRight()))
                  kara.turnLeft();
                  kara.turnLeft();   
                  }
              }
          }


     }
  }
}

        