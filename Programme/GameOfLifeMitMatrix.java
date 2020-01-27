import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class GameOfLifeMitMatrix extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    int MaxGeneration = 1;
    boolean[][] Feld = new boolean[world.getSizeX()][world.getSizeY()];    
    for (int x=0; x<world.getSizeX(); x++) {
      for (int y=0; y<world.getSizeY(); y++) {
        Feld[x][y] = false;
      }
    }
    for (int Generation = 1; Generation <= MaxGeneration; Generation++) {
      for (int XT = 0; XT < world.getSizeX(); XT++) {
        for (int YT = 0; YT < world.getSizeY(); YT++) {
          Feld[XT][YT] = aendernGucken(XT, YT, nachbarzaehlung(XT, YT));
        }      
      }
      for (int XT = 0; XT < world.getSizeX(); XT++) {
        for (int YT = 0; YT < world.getSizeY(); YT++) {
          aendernAktion(XT, YT, Feld[XT][YT]);
        }      
      }
    }
  }

  public int nachbarzaehlung(int XT, int YT) {
    int Nachbarn = 0;
    for (int x= -1; x<=1; x++) {
      for (int y= -1; y<=1; y++) {
        if    (  ! ((x==0) && (y==0))                                            //eigenes Feld zählt nicht als Nachbar
               &&! (XT+x<0 || YT+y<0)                                            //linker und obere Rand wird ausgenommen
               &&! (XT+x>world.getSizeX()-1 || YT+y>world.getSizeY()-1) ) {      //rechter und unterer Rand wird ausgenommen
          if (world.isLeaf(XT+x, YT+y)) {
            Nachbarn++;
          }
        }
      }
    }
    return Nachbarn;
  }

  public void aendernAktion(int XT, int YT, boolean  FeldWechseln) {
    if (FeldWechseln) {
      world.setLeaf(XT, YT, (!FeldWechseln == world.isLeaf(XT, YT)));
    }
  }

  public boolean aendernGucken(int XT, int YT, int Nachbarn) {
    if       ( (Nachbarn == 2)
            || (Nachbarn == 3 && world.isLeaf(XT,YT))
            || (!(Nachbarn == 3) && !world.isLeaf(XT,YT))) {            //Problem(gelöst): ursprünglich änderte er NICHT, wenn 3Nachbarn und kein Blatt
                                                                                                 //tools.println(Integer.toString(0));
      return false;
    }else{
      return true;
    }
  }
}

        