import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class KaraBlasterohneZusätze extends JavaKaraProgram {
  // hier können Sie eigene Methoden definieren
  //Definieren aller Klassenvariablen
    int TickMax = 10;                                                                                  //Setzt die Anzahl aller Ticks pro Level (klassisch: 23)
    int Leben = 7;
  public void myProgram() {
                                                                          tools.println("---------------");
    // hier kommt das Hauptprogramm hin, zB:
  //Definieren aller globalen Variablen
    //Level
    int Level = 0;
    boolean GameOver = false;
    //Zeit
    boolean[] Zeit = new boolean[25];
    for (int i = 0; i<=24; i++) {                                                                      //Fortschrittsbalken zurücksetzen
      Zeit[i] = false;
    }
    //Autoposition (y ist immer 22)
    int Autoposition = 13;
    int[][] Autoform = {{0,-1},{-1,0},{0,0},{1,0},{0,1},{-1,2},{0,2},{1,2}};
   //Ende der Variablendeklaration    
    
   //Aufbau des Spielfeldes
    spielfeldAufbau(Autoform);
   
  /*Kern des Programmes, in dem über eine for-Schleife die Level (max. 9) und die "Ticks" (=23) der Level ausgewertet werden.
    Sollte der Spieler alle Leben verlieren, so werden über die Variable "GameOver" diese Schleifen beide beendet.
  */
    while (Level <= 9 && !GameOver) {
      for (int Fortschritt = 1; Fortschritt <=TickMax && !GameOver; Fortschritt++) {    // später 23 anstatt 12    
        hindernisseErzeugen(Fortschritt);                                          //erzeugen neuer Hindernisse am oberen Spielfeldrand, in Abhängigkeit von "Fortschritt"
        Autoposition = autoBewegen(Autoposition, Leben, Autoform);                 //Steuern des Autos, mit Abfrage einer seitl. Kollision
        Leben = autoKollision(Leben, Autoposition);                                //Abfrage, ob das Auto mit einem Blatt/Pilz kollidiert
        hindernisseBewegen();                                                      //Bewegung der Hindernisse dem Auto entgegen
        Zeit = anzeigenAendern(Fortschritt, Zeit, Leben, Level);                   //aktualisieren aller Anzeigen
        GameOver = alleLebenSindWeg(Leben);                                        //Prüfen, ob der Spieler alle Leben verloren hat

        tools.sleep(200);                                                          //Länge eines "Ticks", original: 4000-(250*Level)           
      }
      world.setMushroom(tools.random(20)+4,0,true);                                //generieren eine Pilzes am Ende jedes Levels
      Level++;                                                                     //erhöhen des Levelzählers
    }
  //Ende: Kern des Programms

    siegNiederlage(Level, GameOver);                                               //gibt Nachricht aus, ob das Spiel gewonnen wurde oder nicht
    
  }

//
//Ab hier werden einzelne Unterfunktionen definiert
//


/*Die Funktion aktualisiert die Anzeigen zu Leben, Level, Fortschritt des Levels, globale binäre Uhr.
    Übergabeveriablen:  int Fortschritt
                        boolean[] Zeit
                        int Leben
                        int Level
    Ausgabe:   boolean[] Zeit
*/
   public boolean[] anzeigenAendern(int Fortschritt, 
                                    boolean[] Zeit,  
                                    int Leben,
                                    int Level) {
    //Fortschrittsbalken
    for (int i = 0; i<= 23; i++) {
      world.setLeaf(23-i, 26, i<Fortschritt);                       
    }
    //Zeit
    for(int i=1; i<=23; i++) {                                                         
      if (!Zeit[i]) {
        world.setLeaf(1+(23-i), 28, true);
        Zeit[i]= true;
        for(int j = 1; j<= (i-1); j++) {        
          world.setLeaf(1+ (23-i)+(j), 28, false);
          Zeit[j]=false;
        }
        i = 100;                                                                        //i = 100 beendet die while-Schleife, wenn die Uhr aktualisiert wurde                                                                          
      }
      if (Zeit[23])                                                                     // setzt die Uhr zurück wenn der 24ste Platz auf "true" gesetzt wurde. Die uhr hat 23 Plätze.
        for(int j = 0; j <= 23; j++) {
          Zeit[j] = false;
        }
      }
    //Levelanzeige
    int[][][] Levelanzeige ={{{0,1,0},{0,1,0},{0,1,0},{0,1,0},{0,1,0}},                 //Festhalten aller Pixelpositionen für die Levelanzeige.                      
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
        for (int x = 0; x <= 2; x++) {                                                  //fr-Schleifen Kombination, schaltet Pixel an/aus
          for (int y =0; y<=4; y++) {
            world.setLeaf(x,y+11,Levelanzeige[i][y][x]==1);                                                                          //war mal Prblem: for-Schleife von 11-15 in Verbindung mit Levelanzeige
          }
        }
      }
    }
   //Lebensanzeige
    for (int i =1; i<=7; i++) {
      world.setMushroom(1, i+1, i<=Leben);                                             //for-Schleife setzt/löscht soviele Pilz, dass von oben gezählt an noch "Leben" da sind
    }
    return Zeit;                                                                       //Ausgabe von Zeit, da Zeit global weiter gezählt wird
  }


/*  Die Funktion erzeugt alle 4 "Ticks" ein neues Kleeblatt zufällig am oberen Spielfeldrand.
    Übergabewert: int i (int Fortschritt aus obiger Schleife)
    Ausgeb: nichts
*/
  public void hindernisseErzeugen(int i) {                                                                                        
    if (i == 0 || i== 4 || i== 8 || i== 12 || i== 16 || i== 20) {                      //MemoAnMich: ginge besser mit Restbetrachtung/Modulo
      world.setLeaf(tools.random(20)+4,0,true);
    }
  }


/*  Die Funktion bewegt die Pilze und Blätte auf dem Spielfeld um ein Feld nach unten. 
    Blätter/Pilze am unteren Rand werden vor der Bewegung entfernt.
    die ineinander geschachtelten for-Schleifen "scannen" das Spielfeld und löschen jedes Blatt/Pilz
    Übergabewert: nichts
    Ausgabe: nichts

*/
  public void hindernisseBewegen() {
    for (int x = 4; x<=24; x++) {                                                      
      if (world.isLeaf(x, 24)) {
        world.setLeaf(x, 24, false);
      }
      if (world.isMushroom(x, 24)) {
        world.setMushroom(x, 24, false);
      }
      for (int y=(24-1); y>=0; y= y-1) {                                                                                              //WIchtig: von unten nach Oben auswerten, da sonst Blätter mehrfach bewegt werden
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


  public int autoKollision(int Leben, int Autoposition) {
                          //linke Ecke des Autos
    if (world.isLeaf(Autoposition-1, 22-1)) {
      Leben--;
      world.setLeaf(Autoposition-1, 22-1, false);
    }
    if (world.isMushroom(Autoposition-1, 22-1)) {
      Leben++;
      world.setMushroom(Autoposition-1, 22-1, false);
    }
                          //rechte Ecke des Autos
    if (world.isLeaf(Autoposition+1, 22-1)) {
      Leben--;
      world.setLeaf(Autoposition+1, 22-1, false);
    }
    if (world.isMushroom(Autoposition+1, 22-1)) {
      Leben++;
      world.setMushroom(Autoposition+1, 22-1, false);
    }
                          //Mitte des Autos
    if (world.isLeaf(Autoposition, 22-2)) {
      Leben--;
      world.setLeaf(Autoposition, 22-2, false);
    }
    if (world.isMushroom(Autoposition, 22-2)) {
      Leben++;
      world.setMushroom(Autoposition, 22-2, false);
    }
    if (Leben >= 8) {
      Leben = 7;
    }
    return(Leben);
  }


  public int autoBewegen(int Autoposition, int Leben, int[][] Autoform) {
    for(int i=0; i<=7; i++) {     
      world.setTree(Autoposition+Autoform[i][0], 22+Autoform[i][1],false);
    }
    if (kara.treeFront() && Autoposition<23) {
      Autoposition++;
    }else if (kara.mushroomFront() && Autoposition >5) {
      Autoposition--;
    }                                                                                                                                                  //ansonsten bleibt das Auto an Ort und Stelle

    for(int i=0; i<=7; i++) {
      if (!(world.isEmpty(Autoposition+Autoform[i][0], 22+Autoform[i][1]))) {
        if (world.isLeaf(Autoposition+Autoform[i][0], 22+Autoform[i][1])) {
          world.setLeaf(Autoposition+Autoform[i][0], 22+Autoform[i][1],false);
          Leben--;
        }else{
          world.setMushroom(Autoposition+Autoform[i][0], 22+Autoform[i][1],false);
          Leben++;
        }
      }
      world.setTree(Autoposition+Autoform[i][0], 22+Autoform[i][1],true);
    } 
    return Autoposition;
  }




  public boolean alleLebenSindWeg(int Leben) {
    if (Leben < 0) {
      return true;
    }else{ 
      return false;
    }
  }


  public void siegNiederlage(int Level, boolean GameOver) {
    if (GameOver) {
      tools.showMessage("Verloren! Du hast Level "+Integer.toString(Level)+" erreicht.");                                                                //Info: Aneinanderketung von Strings mit +
    } else {
      tools.showMessage("Gewonnen!");
    }
  }

/* Die Funktion löscht das Spielfeld und erzeugt ein neues Feld mit zufällig generierten Hindernissen.
   Übergabewert: int[][] Autoform
   Ausgabewert: nichts
*/
  public void spielfeldAufbau(int[][] Autoform) {
  //Welt zurücksetzen
    world.clearAll();
    world.setSize(25,35);
  //generieren der Steuerung
    kara.setPosition(1,23);
    kara.turnLeft();
    world.setTree(2,23,true);
    world.setMushroom(0,23,true);
  //generieren aller Balken
    for (int i= 0; i<=2; i++) {
      world.setTree(i,1,true);
      world.setTree(i,9,true);
      world.setTree(i,21,true);
    }
    for (int j = 0; j<=24; j++) {
    world.setTree(j,25,true);
    world.setTree(j,27,true);
    world.setTree(j,29,true);
    }
    for (int k=0; k<25; k++) {
      world.setTree(3,k,true);
    }
  //generieren des Autos
    world.setTree(0,26,true);
    world.setTree(0,28,true);
    world.setTree(24,26,true);
    world.setTree(24,28,true);
    for (int l =0; l<=7; l++) {
      world.setTree(13+Autoform[l][0], 22+Autoform[l][1], true);
    }
  //generieren von zufälligen Anfangshindernissen, in den Reihen 0,4,8 von oben aus gezählt
    for (int n = 0; n<=2; n++) {
      world.setLeaf(4+tools.random(20),4*n,true);
    }
  }


}

  
        