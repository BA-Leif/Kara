import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class FindeBaum extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    tools.println("-----------------------------------------");
    java.awt.Point abc = new java.awt.Point(3,3);
    java.awt.Point Kara = new java.awt.Point(1,1);
    Kara = kara.getPosition();
    tools.println(abc.toString());
    tools.println(Kara.toString());
    
    kara.setPosition(6,3);
  }
}

        