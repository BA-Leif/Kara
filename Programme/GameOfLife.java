import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class GameOfLife extends JavaKaraProgram {

  // hier k�nnen Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:

                    //noch ein Problem: Der linke nachbar ist evtl. weg, obwohl er da war!


    int Size = 6;
    int Counter = 1;
    int Nachbarn = 0;
    int WarMalAnders = 0;

    while (1 == 1) {
        while (Counter <= Size) {
            Counter = Counter + 1;
// Nachbarn z�hlen, Stratausrichtung nach rechts
            Nachbarn = 0;
            kara.move();
            if (kara.onLeaf()) 
                { Nachbarn = Nachbarn + 1; }
            kara.turnRight();
            kara.move();
            if (kara.onLeaf()) 
                { Nachbarn = Nachbarn + 1; }
            kara.turnRight();
            kara.move();
            if (kara.onLeaf()) 
                { Nachbarn = Nachbarn + 1; }
            kara.move();
            if (kara.onLeaf()) 
                { Nachbarn = Nachbarn + 1; }
            kara.turnRight();
            kara.move();
            if (WarMalAnders == 0 ) {                        //wurde vor der Z�hlung evtl. ge�ndert
                if (kara.onLeaf())                 
                    { Nachbarn = Nachbarn + 1; }
            } else if (WarMalAnders == 1) {
                if (!kara.onLeaf())                 
                    { Nachbarn = Nachbarn + 1; }
            }
            kara.move();
            if (kara.onLeaf()) 
                { Nachbarn = Nachbarn + 1; }
            kara.turnRight();
            kara.move();
            if (kara.onLeaf()) 
                { Nachbarn = Nachbarn + 1; }
            kara.move();
            if (kara.onLeaf()) 
                { Nachbarn = Nachbarn + 1; }
            kara.turnRight();
            kara.move();
            kara.turnRight();
            kara.move();
            kara.turnRight();
            kara.turnRight();
// Ende der Z�hling
            WarMalAnders = 0;        //Anpassung f�r die Vergangenheitsz�hlung zur�cksetzen
            if (Nachbarn == 2) {
                //nichts tun
            }
            else if (Nachbarn == 3 && !kara.onLeaf()) {
                kara.putLeaf();
                WarMalAnders = 1;     
            }
            else if (kara.onLeaf()){              
                kara.removeLeaf();      
                WarMalAnders = 1;      
            }






            kara.move();
        }
        kara.turnRight();
        kara.move();
        kara.turnLeft();
        Counter = 1;
    }
  }
}

        