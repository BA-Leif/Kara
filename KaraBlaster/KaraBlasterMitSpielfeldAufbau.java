import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class KaraBlasterMitSpielfeldAufbau extends JavaKaraProgram {
  // hier können Sie eigene Methoden definieren
  public void myProgram() {
                                                                          tools.println("---------------");
    // hier kommt das Hauptprogramm hin, zB:
    //Leben init
    int Leben = 7;
    //Level init
    int Level = 0;
    boolean GameOver = false;
    //Zeit init
    boolean[] Zeit = new boolean[25];
    for (int i = 0; i<=24; i++) {
      world.setLeaf(1+ (23-i), 28, false);                                                                      //Uhr zurücksetzen
      world.setLeaf(1+ (23-i), 26, false);                                                                      //Fortschrittsbalken zurücksetzen
      Zeit[i] = false;
    }
    //Autoposition (y ist immer 22)
    int Autoposition = 13;
    int[][] Autoform = {{0,-1},{-1,0},{0,0},{1,0},{0,1},{-1,2},{0,2},{1,2}};
    //Ende der Variablendeklaration    
    
    spielfeldAufbau(Autoform);
                                                                                    //FRAGE: kann man in die for-Schleife auch ZUSÄTZLICH eine Booleanabfrage einsetzen?
    while (Level <= 9 && !GameOver) {
      for (int Fortschritt = 1; Fortschritt <=12 && !GameOver; Fortschritt++) {                                                // später 23 anstatt 7    
        hindernisseErzeugen(Fortschritt);
        Autoposition = autoBewegen(Autoposition, Leben, Autoform);
        Leben = autoKollision(Leben, Autoposition);
        hindernisseBewegen();
        Zeit = anzeigenAendern(Fortschritt, Zeit, Leben, Level);
        GameOver = alleLebenSindWeg(Leben);                             //innere Schleife abbrechen

        tools.sleep(200);                                                                                        //statt 500 : 4000-(250*Level)   
      }
      for (int i = 0; i <= 24; i++) {
        world.setLeaf(24-i, 26, false);         
      }
      world.setMushroom(tools.random(20)+4,0,true);
      Level++;
    }
    siegNiederlage(Level, GameOver);
    
  }






   public boolean[] anzeigenAendern(int Fortschritt, 
                                    boolean[] Zeit,  
                                    int Leben,
                                    int Level) {
    //Fortschritt
    world.setLeaf(24-Fortschritt, 26, true);
    //Zeit
    for(int i=1; i<=23; i++) {                                                          //zum Testen:
      if (!Zeit[i]) {
        world.setLeaf(1+(23-i), 28, true);
        Zeit[i]= true;
        for(int j = 1; j<= (i-1); j++) {        
          world.setLeaf(1+ (23-i)+(j), 28, false);
          Zeit[j]=false;
        }
        i = 100;                                                                         //obere Zeitschleife abbrechen                                                     
      }
      if (Zeit[23])
        for(int j = 0; j <= 23; j++) {
          Zeit[j] = false;
        }
      }
    //Levelanzeige
    int[][][] Levelanzeige ={{{0,1,0},{0,1,0},{0,1,0},{0,1,0},{0,1,0}},                           
                             {{1,1,1},{0,0,1},{1,1,1},{1,0,0},{1,1,1}},
                             {{1,1,1},{0,0,1},{1,1,1},{0,0,1},{1,1,1}},
                             {{1,0,0},{1,0,1},{1,1,1},{0,0,1},{0,0,1}},
                             {{1,1,1},{1,0,0},{1,1,1},{0,0,1},{1,1,1}},
                             {{1,1,1},{1,0,0},{1,1,1},{1,0,1},{1,1,1}},
                             {{1,1,1},{0,0,1},{0,0,1},{0,0,1},{0,0,1}},
                             {{1,1,1},{1,0,1},{1,1,1},{1,0,1},{1,1,1}},
                             {{1,1,1},{1,0,1},{1,1,1},{0,0,1},{1,1,1}}
                            };
    for (int i = 0; i<=8; i++) {
      if (Level == i) {
        for (int x = 0; x <= 2; x++) {
          for (int y =0; y<=4; y++) {
            world.setLeaf(x,y+11,Levelanzeige[i][y][x]==1);                                                                          //war mal Prblem: for-Schleife von 11-15 in Verbindung mit Levelanzeige
          }
        }
      }
    }
   //Lebensanzeige
    for (int i =1; i<=7; i++) {
      world.setMushroom(1, i+1, i<=Leben);
    }
    return Zeit;
  }


  public void hindernisseErzeugen(int i) {                                                                                        //i = Fortschritt
    if (i == 0 || i== 4 || i== 8 || i== 12 || i== 16 || i== 20) {                               //ginge besser mit Restbetrachtung/Modulo
      world.setLeaf(tools.random(20)+4,0,true);
    }
  }


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


  public void spielfeldAufbau(int[][] Autoform) {
    //Welt zurücksetzen
    world.clearAll();
    world.setSize(25,35);
    //Erzeugen der Steuerung
    kara.setPosition(1,23);
    kara.turnLeft();
    world.setTree(2,23,true);
    world.setMushroom(0,23,true);
    //Erzeugen aller Balken
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
    //Erzeugen des Autos
    world.setTree(0,26,true);
    world.setTree(0,28,true);
    world.setTree(24,26,true);
    world.setTree(24,28,true);
    for (int l =0; l<=7; l++) {
      world.setTree(13+Autoform[l][0], 22+Autoform[l][1], true);
    }
    //Erzeugen von Anfangshindernissen
    for (int n = 0; n<=2; n++) {
      world.setLeaf(4+tools.random(20),4*n,true);
    }
  }


}

  
        