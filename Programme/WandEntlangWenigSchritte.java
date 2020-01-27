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

  // hier k�nnen Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    if (!kara.treeFront()) {
        kara.move();                                              //sehr unsch�n, aber mir f�llt nichts besseres f�r die zweite Welt ein, ohne ginge Kara falsch
    }
    while (!kara.onLeaf()) {

        if (!kara.treeFront()  &&  (!kara.treeRight() && !kara.treeLeft())) {                      //WICHTIG: muss vor dem ersten elseif sein, da dieser Fall dort auch zutr�fe
            kara.turnRight();                                                                                //optional: zweiter Bed. negieren
            kara.move();
        }
        else if (!kara.treeFront()  &&  (kara.treeRight() || kara.treeLeft())) {                  //else if anstatt if-Verschachtelungen    & ODER, jetzt nicht mehr als Konstuktion mit && und !
            kara.move();
        }
        else if (kara.treeRight()) {                                                             //bei einer Sackgasse w�rde sich Kara zweimal nach rechts drehen, daher ist der TL&TL-Fall unn�tig
            kara.turnLeft();
        } 
        else {        
            kara.turnLeft();
        }
    }
  }
}

        