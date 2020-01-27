import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class BlaetterLegen extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    
    if (!kara.onLeaf()){
      kara.putLeaf();
    } else {
      kara.removeLeaf();
    }

    while (!kara.treeFront()){
      kara.move();
      if (!kara.onLeaf()){
        kara.putLeaf();
      } else {
        kara.removeLeaf();
      }
    }  
  }
}


        