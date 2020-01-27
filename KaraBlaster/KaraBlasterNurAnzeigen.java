import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class KaraBlasterNurAnzeigen extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren





  public void myProgram() {
                                                                          tools.println("---------------");
    // hier kommt das Hauptprogramm hin, zB:
    //Leben init
    int Leben = 2;
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
 
    //Ende der Variablendeklaration    
    while (Level <= 9) {
      for (int Fortschritt = 1; Fortschritt <=23; Fortschritt++) {                                  // später 23 anstatt 7    
        Zeit = anzeigenAendern(Fortschritt, Zeit, Leben, Level, Levelanzeige);
        tools.sleep(50);
      }
      for (int i = 0; i <= 24; i++) {
        world.setLeaf(24-i, 26, false);                                                        //statt 500 : 4000-(250*Level)        
      }
tools.println(Integer.toString(Level));
      Level = Level + 1;
    }
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



}

  
        