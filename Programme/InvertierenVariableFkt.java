import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class InvertierenVariableFkt extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    boolean LookRight = true;
    boolean ErsteBahn = true;
    while (!kara.treeFront()) {                //Problem: treeFront, ist aber unsinnig, da Kara an dieser Stelle nie in der 
      while (!kara.treeFront()) {                 //Situation sein kann, dass ein Baum davor ist.
        invertieren();
        kara.move();
      }

      invertieren();                      //Wurde zuerst vergessen, dass beim umdrehen auch invertiert werden sollte
      
      if ((!kara.treeRight() && !kara.treeLeft()) || ErsteBahn) {       //neues Problem: erste Reihe erfüllt auch diese Bedingung  LÖSUNG: Variable um diesen Fall auszunehmen
        ErsteBahn = false;                  
        umdrehen(LookRight);
        LookRight = !LookRight;                  //ehemaliges Problem: LookRight kann scheinbar nicht in der Funktion geändert werden, also wird die Änderung ausgelagert
      }
    }
  }


  public void invertieren() {
      if (kara.onLeaf()) {                           
        kara.removeLeaf();
      } else {
        kara.putLeaf();
      }           
  }

  public void umdrehen(boolean B) {
    if (B) {                        //Problem: Kara rennt in den letzten Baum.
      kara.turnRight();                     //Lösung: Aufhören, wenn links bzw. rechts ein Baum steht --> behebt auch obiges Problem
      kara.move();
      kara.turnRight();
    } else {
      kara.turnLeft();
      kara.move();
      kara.turnLeft();
    }
  }

}                                                    

        