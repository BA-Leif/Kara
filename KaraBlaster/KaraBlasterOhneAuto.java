import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class KaraBlasterOhneAuto extends JavaKaraProgram {
  // hier können Sie eigene Methoden definieren
  public void myProgram() {
                                                                          tools.println("---------------");
    // hier kommt das Hauptprogramm hin, zB:
    //Leben init
    int Leben = 7;
    //Level init
    int Level = 0;
//    int[][][] Levelanzeige = new int[9][5][3];
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
    //Zeit init
    boolean[] Zeit = new boolean[25];
    for (int i = 0; i<=24; i++) {
      world.setLeaf(1+ (23-i), 28, false);
      Zeit[i] = false;
    }
    //Autoposition (y ist immer 22)
    int Autoposition = 13; 

    //Ende der Variablendeklaration    
    while (Level <= 9) {
      for (int Fortschritt = 1; Fortschritt <=12; Fortschritt++) {                                  // später 23 anstatt 7    
        hindernisseErzeugen(Fortschritt);
        Leben = autoKollision(Leben, Autoposition);
        hindernisseBewegen();
        Zeit = anzeigenAendern(Fortschritt, Zeit, Leben, Level, Levelanzeige);
        Level = alleLebenSindWeg(Leben, Level);
        tools.sleep(100);                                                                            //statt 500 : 4000-(250*Level)   
      }
      for (int i = 0; i <= 24; i++) {
        world.setLeaf(24-i, 26, false);         
      }
      world.setMushroom(tools.random(20)+4,0,true);
      Level++;
    }
    siegNiederlage(Level);
    
  }






   public boolean[] anzeigenAendern(int Fortschritt, 
                                    boolean[] Zeit,  
                                    int Leben,
                                    int Level, int[][][] Levelanzeige) {
    //Fortschritt
    world.setLeaf(24-Fortschritt, 26, true);
    //Zeit
    for(int i=1; i<=23; i++) {
      if (!Zeit[i]) {
        world.setLeaf(1+(23-i), 28, true);
        Zeit[i]= true;
        for(int j = 1; j<= (i-1); j++) {        
          world.setLeaf(1+ (23-i)+(j), 28, false);
          Zeit[j]=false;
        } 
        i = 100;                     //obere Zeitschleife abbrechen                                                     
      }
    }
    //Levelanzeige
    for (int i = 0; i<=8; i++) {
      if (Level == i) {
        for (int x = 0; x <= 2; x++) {
          for (int y =0; y<=4; y++) {
            world.setLeaf(x,y+11,Levelanzeige[i][y][x]==1);                    //war mal Prblem: for-Schleife von 11-15 in Verbindung mit Levelanzeige
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


  public void hindernisseErzeugen(int i) {
    if (i == 0 || i== 4 || i== 8 || i== 12 || i== 16 || i== 20) {     //ginge besser mit Restbetrachtung/Modulo
      world.setLeaf(tools.random(20)+4,0,true);
    }
  }


  public void hindernisseBewegen() {
    for (int x = 4; x<=24; x++) {
      for (int y=(24-1); y>=0; y= y-1) {                              //WIchtig: von unten nach Oben auswerten, da sonst Blätter mehrfach bewegt werden
        if (world.isLeaf(x, y)) {
          world.setLeaf(x, y, false);
          world.setLeaf(x, y+1, true);
        }
        if (world.isMushroom(x,y)) {
          world.setMushroom(x, y, false);
          world.setMushroom(x, y+1, true);
        }
      }
      if (world.isLeaf(x, 24)) {
        world.setLeaf(x, 24, false);
      }
      if (world.isMushroom(x, 24)) {
        world.setMushroom(x, 24, false);
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


  public int alleLebenSindWeg(int Leben, int Level) {
    if (Leben <= 0) {
      Level = 13;
    }
    return Level;
  }


  public void siegNiederlage(int Level) {
    if (Level == 14) {
      //Verloren!
    } else {
      //Gewonnen!
    }
  }



}

  
        