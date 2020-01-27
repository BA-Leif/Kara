import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class GameOfLife10x10 extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:

                    //noch ein Problem: Der linke nachbar ist evtl. weg, obwohl er da war!.....behoben.... ABER
                            
                    //die oberen Nachbarn wurden auch vorher geändert....behoben durch mehr mehr Variablen


                  //GANZ NEUER ANSATZ: ein n-Tupel oder nxn Matrix verwenden, kara trägt zuerst ein, welche Felder geändert werden müssten, dann im Anschluss ändert Kara alle Felder
              


    int Size = 10;
    int Counter = 1;
    int Nachbarn = 0;
    int WarMalAnders1 = 0;
    int WarMalAnders2 = 0;
    int WarMalAnders3 = 0;
    int WarMalAnders4 = 0;
    int WarMalAnders5 = 0;
    int WarMalAnders6 = 0;
    int WarMalAnders7 = 0;
    int WarMalAnders8 = 0;
    int WarMalAnders9 = 0;
    int WarMalAnders10 = 0;
    int WarMalAnders11 = 0;

    while (1 == 1) {
        while (Counter <= Size) {
            Counter = Counter + 1;
// Nachbarn zählen, Stratausrichtung nach rechts
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
            if (WarMalAnders1 == 0 ) {                        //wurde vor der Zählung evtl. geändert
                if (kara.onLeaf())                 
                    { Nachbarn = Nachbarn + 1; }
            } else if (WarMalAnders1 == 1 ) {
                if (!kara.onLeaf())                 
                    { Nachbarn = Nachbarn + 1; }
            }                                                 //bis hier     
            kara.move();
            if (WarMalAnders11 == 0 ) {                        //wurde vor der Zählung evtl. geändert
                if (kara.onLeaf())                 
                    { Nachbarn = Nachbarn + 1; }
            } else if (WarMalAnders11 == 1 ) {
                if (!kara.onLeaf())                 
                    { Nachbarn = Nachbarn + 1; }
            }                                                 //bis hier}
            kara.turnRight();
            kara.move();
            if (WarMalAnders10 == 0 ) {                        //wurde vor der Zählung evtl. geändert
                if (kara.onLeaf())                 
                    { Nachbarn = Nachbarn + 1; }
            } else if (WarMalAnders10 == 1 ) {
                if (!kara.onLeaf())                 
                    { Nachbarn = Nachbarn + 1; }
            }                                                 //bis hier
            kara.move();
            if (WarMalAnders9 == 0 ) {                        //wurde vor der Zählung evtl. geändert
                if (kara.onLeaf())                 
                    { Nachbarn = Nachbarn + 1; }
            } else if (WarMalAnders9 == 1 ) {
                if (!kara.onLeaf())                 
                    { Nachbarn = Nachbarn + 1; }
            }                                                 //bis hier
            kara.turnRight();
            kara.move();
            kara.turnRight();
            kara.move();
            kara.turnRight();
            kara.turnRight();
// Ende der Zählung
            WarMalAnders11 = WarMalAnders10;        //Weiterschiebung der Vergangenheitszähler
            WarMalAnders10 = WarMalAnders9;
            WarMalAnders9  = WarMalAnders8;
            WarMalAnders8  = WarMalAnders7;
            WarMalAnders7  = WarMalAnders6;                                    //Variablen habe ich zuerst in die falsche Richtung verschoben!! ...doch nicht. War am Anfang schon richtig
            WarMalAnders6  = WarMalAnders5;
            WarMalAnders5  = WarMalAnders4;
            WarMalAnders4  = WarMalAnders3;
            WarMalAnders3  = WarMalAnders2;
            WarMalAnders2  = WarMalAnders1;
            WarMalAnders1  = 0;                    //Ende der Verschiebung

            if (Nachbarn == 2) {
                //nichts tun
            }
            else if (Nachbarn == 3 && !kara.onLeaf()) {
                kara.putLeaf();
                WarMalAnders1 = 1;     
            }
            else if (!(Nachbarn == 3) && kara.onLeaf()){              
                kara.removeLeaf();      
                WarMalAnders1 = 1;      
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

        