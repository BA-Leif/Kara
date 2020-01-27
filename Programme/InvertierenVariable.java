import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class InvertierenVariable extends JavaKaraProgram {

  // hier k�nnen Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    boolean LookRight = true;
    boolean ErsteBahn = true;
    while (!kara.treeFront()) {                //Problem: treeFront, ist aber unsinnig, da Kara an dieser Stelle nie in der 
      while (!kara.treeFront()) {                 //Situation sein kann, dass ein Baum davor ist.
        if (kara.onLeaf()) {                                            //L�sung: durch Variable wird das drehen �berprungen, und der Fall kann eintreten              
          kara.removeLeaf();
        } else {
          kara.putLeaf();
        }
        kara.move();
      }

      if (kara.onLeaf()) {                            //Wurde zuerst vergessen, dass beim umdrehen auch invertiert werden sollte
        kara.removeLeaf();
      } else {
        kara.putLeaf();
      }           
      
      if ((!kara.treeRight() && !kara.treeLeft()) || ErsteBahn) {       //neues Problem: erste Reihe erf�llt auch diese Bedingung  L�SUNG: Variable um diesen Fall auszunehmen
        ErsteBahn = false;                  
        if (LookRight) {                        //Problem: Kara rennt in den letzten Baum.
          kara.turnRight();                     //L�sung: Aufh�ren, wenn links bzw. rechts ein Baum steht --> behebt auch obiges Problem
          kara.move();
          kara.turnRight();
          LookRight = false;
        } else {
          kara.turnLeft();
          kara.move();
          kara.turnLeft();
          LookRight = true;
        }
      }
    }
  }
}                                                    

        