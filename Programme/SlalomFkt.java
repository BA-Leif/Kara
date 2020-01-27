import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class SlalomFkt extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    boolean Richtung = true;     //true -> Links
  
    while (true) {
      do {                                                  //hier war do-while sinnvoll
        karaKurve(Richtung);
      } while (!(kara.treeRight() && kara.treeLeft()));
      Richtung = !Richtung;
    }
  }


  void karaKurve (boolean RichtungFkt) {
    if (RichtungFkt) {
      kara.move();
      kara.turnLeft();
      kara.move();
    }else{
      kara.move();
      kara.turnRight();
      kara.move();
    }
  }
}

        