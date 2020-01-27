import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class WandEntlangWenigSchritte extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    if (!kara.treeFront()) {
        kara.move();                                              //sehr unschön, aber mir fällt nichts besseres für die zweite Welt ein, ohne ginge Kara falsch
    }
    while (!kara.onLeaf()) {

        if (!kara.treeFront()  &&  (!kara.treeRight() && !kara.treeLeft())) {                      //WICHTIG: muss vor dem ersten elseif sein, da dieser Fall dort auch zuträfe
            kara.turnRight();                                                                                //optional: zweiter Bed. negieren
            kara.move();
        }
        else if (!kara.treeFront()  &&  (kara.treeRight() || kara.treeLeft())) {                  //else if anstatt if-Verschachtelungen    & ODER, jetzt nicht mehr als Konstuktion mit && und !
            kara.move();
        }
        else if (kara.treeRight()) {                                                             //bei einer Sackgasse würde sich Kara zweimal nach rechts drehen, daher ist der TL&TL-Fall unnötig
            kara.turnLeft();
        } 
        else {        
            kara.turnLeft();
        }
    }
  }
}

        