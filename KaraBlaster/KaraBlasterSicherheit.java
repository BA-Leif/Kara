import javakara.JavaKaraProgram;

/* Ziele zur nächsten Version:

    Dokumentation muss immer angepasst werden
    PAP auch...
    carShape vom oberen rechten Pixel des umfassenden Rechteckes aus angeben
    carCoordinates geben den Punkt der oberen, linken Ecke des Rechteckes an, welches das Auto umfasst

    Anzahl der neuen Blätter variiert je nach Level


    folgend: eigenes Auto zeichnen (in unteren 5 Reihen, 20sek Zeit)

    Kara soll sich nicht wegbewegenkönnen (so semi behoben, da die Abfrage nur jeden Tick aktiviert wird)
   */

        
public class KaraBlasterVer1_05 extends JavaKaraProgram {

  //Definieren aller Klassenvariablen                                                                                      
  int Lives = 4;                                                                        //Setzt die Leben zum Start auf Wert. Maximale Leben sind 7.
  int[] carCoordinates = {13,22};                                                      //Bibt den Punkt der oberen, linken Ecke des kleinstmöglichen, das Auto enthaltenden Rechteckes an




  public void myProgram() {
                                                                          tools.println("---------------");
    // hier kommt das Hauptprogramm hin, zB:
  //Definieren aller StartVariablen
    //Level
    int Level = 0;                                                                     //Setzt das Startlevel auf Wert+1
    boolean GameOver = false;                                                          //GameOver = true: Der Spieler hat alle Leben aufgebraucht
    int TickMax = 10;                                                                  //Setzt die Anzahl aller Ticks bis das nächste Level erreicht ist (klassisch: 23)
//    int[] TickDelay = {2000, 1800, 1600, 1400, 1200, 1000, 900, 800, 750, 700, 650};   //Setzt die Pausen zwischen den einzelnen Ticks, [X]: entspricht Level
    int[] TickDelay = {200, 200, 200, 200, 200, 200, 200, 200, 200, 200};   //TESTVARIABLE                                
    //Zeit
    boolean[] Time = new boolean[25];
    for (int i = 0; i<=24; i++) {                                                                    
      Time[i] = false;
    }
    //Form des Autos
int[][] CarShape = new int[8][2];
CarShape = carCreate();
//    int[][] CarShape = {{0,1},{0,3},{1,0},{1,1},{1,2},{1,3},{2,1},{2,3}};             //Enthält alle Pixel des Autos
                                                                                        //    gemessen vom oberen, linken Pixel des kleinstmöglichen Rechteckes, welches das Auto enthält 

   //Aufbau des Spielfeldes und aller Anzeigen
    generateScreen(CarShape);
   
  /*Kern des Programmes, in dem über eine for-Schleife die Level (max. 9) und die Ticks der Level ausgewertet werden.
    Ein Level dauert "MaxTicks", bevor es zum nächsten Level springt
    Sollte der Spieler alle Leben verlieren, so werden über die Variable "GameOver" diese Schleifen beide beendet.
  */
    while (Level <= 9 && !GameOver) {
      for (int LevelProgress = 1; LevelProgress <=TickMax && !GameOver; LevelProgress++) {
        obstaclesSpawn(LevelProgress);                                                    //erzeugen neuer Hindernisse am oberen Spielfeldrand, in Abhängigkeit von "Fortschritt"
        carCollision(CarShape);                                                           //Abfrage, ob das Auto mit einem Blatt/Pilz kollidiert
        obstaclesMove();                                                                  //Bewegung der Hindernisse dem Auto entgegen
        carMove(CarShape);                                                                //Steuern des Autos, mit Abfrage einer seitl. Kollision
        Time = displaysChange(LevelProgress, Time, Level);                                //aktualisieren aller Anzeigen
        GameOver = lostAllLives();                                                        //Prüfen, ob der Spieler alle Leben verloren hat
        tools.sleep(TickDelay[Level]);                                                    //Pause zwischen den einzelnen Ticks           
      }
      world.setMushroom(tools.random(20)+4,0,true);                                       //generieren eines Pilzes am Ende jedes Levels
      Level++;                                                                     
    }
  //Ende: Kern des Programms

    winOrLose(Level, GameOver);                                                           //gibt Nachricht aus, ob das Spiel gewonnen wurde oder nicht   
  }



//
//
//
//
//Ab hier werden einzelne Unterfunktionen definiert
//


/*Die Funktion aktualisiert die Anzeigen zu Leben, Level, Fortschritt des Levels, globale binäre Uhr. Die Fkt. setzt auch Kara zurück.
    Übergabeveriablen:  int Fortschritt
                        boolean[] Zeit
                        int Leben
                        int Level
    Ausgabe:   boolean[] Zeit
*/
   public boolean[] displaysChange(int LevelProgress, 
                                    boolean[] Time,  
                                    int Level) {
    //Fortschrittsbalken
    for (int i = 0; i<= 23; i++) {
      world.setLeaf(23-i, 26, i<LevelProgress);                       
    }
    //Zeit
    for(int i=1; i<=23; i++) {                                                         
      if (!Time[i]) {
        world.setLeaf(1+(23-i), 28, true);
        Time[i]= true;
        for(int j = 1; j<= (i-1); j++) {        
          world.setLeaf(1+ (23-i)+(j), 28, false);
          Time[j]=false;
        }
        i = 100;                                                                        //i = 100 beendet die while-Schleife, wenn die Uhr aktualisiert wurde                                                                          
      }
      if (Time[23])                                                                     // setzt die Uhr zurück wenn der 24ste Platz auf "true" gesetzt wurde. Die uhr hat 23 Plätze.
        for(int j = 0; j <= 23; j++) {
          Time[j] = false;
        }
      }
    //Levelanzeige
    int[][][] LevelDigits ={{{0,1,0},{0,1,0},{0,1,0},{0,1,0},{0,1,0}},                 //Festhalten aller Pixelpositionen für die Levelanzeige.                      
                             {{1,1,1},{0,0,1},{1,1,1},{1,0,0},{1,1,1}},                 //    Levelanzeige[N][][]:  N entspricht dem aktuellen Level
                             {{1,1,1},{0,0,1},{1,1,1},{0,0,1},{1,1,1}},                 //    Levelanzeige[][y][x]: Pixelan der Stelle (x,y) ist An(1) bzw. Aus(0)
                             {{1,0,0},{1,0,1},{1,1,1},{0,0,1},{0,0,1}},
                             {{1,1,1},{1,0,0},{1,1,1},{0,0,1},{1,1,1}},                //MemoAnMich: besser wäre es mit true & false zu arbeiten
                             {{1,1,1},{1,0,0},{1,1,1},{1,0,1},{1,1,1}},
                             {{1,1,1},{0,0,1},{0,0,1},{0,0,1},{0,0,1}},
                             {{1,1,1},{1,0,1},{1,1,1},{1,0,1},{1,1,1}},
                             {{1,1,1},{1,0,1},{1,1,1},{0,0,1},{1,1,1}}
                            };
    for (int i = 0; i<=8; i++) {
      if (Level == i) {                                                                //wählt das passende Level aus
        for (int x = 0; x <= 2; x++) {                                                  //for-Schleifen Kombination, schaltet Pixel an/aus
          for (int y =0; y<=4; y++) {
            world.setLeaf(x,y+11,LevelDigits[i][y][x]==1);                                                                          //war mal Prblem: for-Schleife von 11-15 in Verbindung mit Levelanzeige
          }
        }
      }
    }
   //Lebensanzeige
    for (int i =1; i<=7; i++) {
      world.setMushroom(1, i+1, i<=Lives);                                             //for-Schleife setzt/löscht soviele Pilz, dass von oben gezählt an noch "Leben" da sind
    }
    return Time;                                                                       //Ausgabe von Zeit, da Zeit global weiter gezählt wird
  }


/*  Die Funktion erzeugt alle 4 "Ticks" ein neues Kleeblatt zufällig am oberen Spielfeldrand.

    Übergabewert: int i (LevelProgress aus umschliessender for-Schleife)
    Ausgabewert: nichts
*/
  public void obstaclesSpawn(int i) {                                                                                        
    if (i % 3 == 0) {                                               
      world.setLeaf(tools.random(20)+4,0,true);
    }
  }


/*  Die Funktion bewegt die Pilze und Blätte auf dem Spielfeld um ein Feld nach unten. 
    Blätter/Pilze am unteren Rand werden vor der Bewegung entfernt.
    die ineinander geschachtelten for-Schleifen "scannen" das Spielfeld und löschen jedes Blatt/Pilz.

    Übergabewert: nichts
    Ausgabewert: nichts
*/
  public void obstaclesMove() {
    for (int x = 4; x<=24; x++) {                                                      
      if (world.isLeaf(x, 24)) {
        world.setLeaf(x, 24, false);
      }
      if (world.isMushroom(x, 24)) {
        world.setMushroom(x, 24, false);
      }
      for (int y = (24-1); y>=0; y= y-1) {                                               //Auswertung von unten nach oben, da sonst Blätter/Pilze mehrfach bewegt werden
        if (world.isLeaf(x, y)) {
          world.setLeaf(x, y, false);
          world.setLeaf(x, y+1, true);
        }
        if (world.isMushroom(x,y)) {
          world.setMushroom(x, y, false);
          world.setMushroom(x, y+1, true);
        }
      }
    }
  }


/*  Die Funktion testet die Kollision mit dem Auto und den entgegenkommenden Hidernissen.
    Die Funktion testet für jeden Pixel des Autos, ob ein Blatt/Pilz daruber ist, Sie entfernt das Hinderniss und passt die "Lives" entsprechend an.

    Übergabewert: int[][] CarShape
    Ausgabewert: nichts
*/
  public void carCollision(int[][] CarShape) { 
    for (int i = 0; i < CarShape.length; i++) {
      if (world.isLeaf(carCoordinates[0] + CarShape[i][0], carCoordinates[1]-1 + CarShape[i][1])) {
        Lives--;
        world.setLeaf(carCoordinates[0] + CarShape[i][0], carCoordinates[1]-1 + CarShape[i][1], false);
      }
      if (world.isMushroom(carCoordinates[0] + CarShape[i][0], carCoordinates[1]-1 + CarShape[i][1])) {
        Lives++;
        world.setMushroom(carCoordinates[0] + CarShape[i][0], carCoordinates[1]-1 + CarShape[i][1], false);
      }
    }
    if (Lives >= 8) {                                                                    //verhindert, dass der Spieler mehr als 7 Leben hat
      Lives = 7;
    }
  }


/*  Die Funktion bewegt das Auto nach links/rechts und überprüft auch ob das Auto seitlich in ein Hinderniss fährt.
    
    Übergabewert: int[][] CarShape
    Ausgabewert: nichts
*/
  public void carMove(int[][] CarShape) {
    java.awt.Point KaraMoved = new java.awt.Point(1,23);                                                                               
    if (!(kara.getPosition().equals(KaraMoved))) {                                        //Setzt Kara zurück zwischen Baum und Pilz, sollte sie den Platz verlassen haben
      kara.setPosition(1,23);
    }
    if (kara.treeFront() || kara.mushroomFront()) {     
                                                                                           // den Rest der Fkt. umfassende Abfrage, die sie überspringt, sollte der Spieler das Auto nicht bewegen wollen
      for(int i=0; i< CarShape.length; i++) {                                                           //Löschen des Autos
        world.setTree(carCoordinates[0]+CarShape[i][0], carCoordinates[1]+CarShape[i][1],false);
      }
      if (kara.treeFront() && carCoordinates[0]<23) {                                                   //Anpassen der Koordinaten des Autos, jenachdem in welche Richtung Kara guckt
        carCoordinates[0]++;
      }else if (kara.mushroomFront() && carCoordinates[0] >5) {
        carCoordinates[0]--;
      }                                                        
      for(int i=0; i<CarShape.length; i++) {                                                            //Erzeugen des Autos an der neuen Position & Kollisionsabfrage
        if (!(world.isEmpty(carCoordinates[0]+CarShape[i][0], carCoordinates[1]+CarShape[i][1]))) {         //Kollisionsabfrage, Löscht ein evtl. Hinderniss und passt "Lives" an
          if (world.isLeaf(carCoordinates[0]+CarShape[i][0], carCoordinates[1]+CarShape[i][1])) {
            world.setLeaf(carCoordinates[0]+CarShape[i][0], carCoordinates[1]+CarShape[i][1],false);
            Lives--;
          }else{
            world.setMushroom(carCoordinates[0]+CarShape[i][0], carCoordinates[1]+CarShape[i][1],false);    
            Lives++;
          }
        }
        world.setTree(carCoordinates[0]+CarShape[i][0], carCoordinates[1]+CarShape[i][1],true);             //Erzeugen des Autos an neuer Position
      } 
    }
  }


/*  Die Funktion überprüft am Ende eines jeden Ticks, ob der Spieler noch Leben hat. Es gibt das "nullte" Leben. 
    Die Ausgabe überschreibt im Hauptprogramm den Wert von GameOver.

    Übergabewert: nichts
    Ausgabewert: boolean
*/
  public boolean lostAllLives() {
    if (Lives < 0) {
      return true;
    }else{ 
      return false;
    }
  }


/*  Die Funktion überprüft nach Ablauf des Hauptteil des Programmes, ob der Spieler gewonnen oder verloren hat und zeigt eine entsprechende Nachricht an.

    Übergabewert: int Level
                  boolean GameOver
    Ausgabewert: nichts
*/
  public void winOrLose(int Level, boolean GameOver) {
    if (GameOver) {
      tools.showMessage("Verloren! Du hast Level "+Integer.toString(Level)+" erreicht.");       
    } else {
      tools.showMessage("Gewonnen!");
    }
  }


/* Die Funktion löscht das Spielfeld und erzeugt ein neues Feld mit zufällig generierten Hindernissen.

   Übergabewert: int[][] Autoform
   Ausgabewert: nichts
*/
  public void generateScreen(int[][] CarShape) {
  //Welt zurücksetzen
    world.clearAll();
    world.setSize(25,35);
  //generieren der Steuerung mit Kara
    kara.setPosition(1,23);
    kara.turnLeft();
    world.setTree(2,23,true);
    world.setMushroom(0,23,true);
  //generieren aller Balken
    for (int i= 0; i<=2; i++) {                                                  //kleine Querbalken
      world.setTree(i,1,true);
      world.setTree(i,9,true);
      world.setTree(i,21,true);
    }
    for (int i = 0; i<=24; i++) {                                                //große Querbalken    
      world.setTree(i,25,true);
      world.setTree(i,27,true);
      world.setTree(i,29,true);
    }
    for (int i=0; i<25; i++) {                                                    //senkrechter Balken
      world.setTree(3,i,true);
    }
    world.setTree(0,26,true);                                                     //Füllen der Lücken zwischen den Balken
    world.setTree(0,28,true);
    world.setTree(24,26,true);
    world.setTree(24,28,true);
  //generieren des Autos
    for (int i =0; i<CarShape.length; i++) {
      world.setTree(carCoordinates[0]+CarShape[i][0], carCoordinates[1]+CarShape[i][1], true);
    }
  //generieren von zufälligen Anfangshindernissen
    for (int i = 0; i<=4; i++) {
      world.setLeaf(4+tools.random(20),4*i,true);
    }
    for (int i = 0; i<1; i++) {
      world.setMushroom(4+tools.random(20),tools.random(16),true);
    }
  }


  public int[][] carCreate() {
    int[][] CarShape = {{0,1},{0,3},{1,0},{1,1},{1,2},{1,3},{2,1},{2,3}};
  
    int MaxCSX = 0;
    int MaxCSY = 0;
    for (int i =0; i<CarShape.length; i++) {                                        //bestimmt die Länge/Höhe des Auto einschliessenden Rechteckes
      if (MaxCSX<CarShape[i][0]) {
        MaxCSX = CarShape[i][0];
      }
      if (MaxCSY<CarShape[i][1]) {
        MaxCSY = CarShape[i][1];
      }
    }

carCoordinates[0] = 14-(MaxCSX/2);                                                  //setzt die neuen Koordinaten des Autos
carCoordinates[1] = 24-MaxCSY;
    return CarShape;
  }


//Ende von allem
}

  
        