import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class WandEntlangKurz extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:

    while (!kara.onLeaf()) {
        if (!kara.treeFront() && (kara.treeRight() || kara.treeLeft())) {
            kara.move();                        
        } else {
            kara.turnRight();                      //Rechtsdrehen, falls Kara im Leeren steht ODER vor einem Baum ist
            if (kara.treeFront()) {                //Baum im Weg: 2Fälle: Sackgasse oder Kara muss nach links
                kara.turnRight();                  //2wei mal drehen um nach links zu gucken
                kara.turnRight();                                  //sollte da dennoch ein Baum sein, dreht sich Kara nach einem zweiten Gesamtablauf so, dass sie die Sackgasse verlassen kann
            } else {
                kara.move();                      //nötig, damit Kara sich im Freien Raum nicht im Kreis dreht
            }
        }        
    }
  }
}

        