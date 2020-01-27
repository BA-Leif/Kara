import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class GameOfLifeKaraFinal extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    int Zeile = world.getSizeY();
    int Spalte = world.getSizeX();
    boolean[][] Feld = new boolean[Zeile][Spalte];
    Feld = feldInitialisieren(Zeile, Spalte);
    while (true) {
      for (int ZJ = 0; ZJ < Zeile; ZJ++) {
        for (int SJ = 0; SJ<Spalte; SJ++) {
          Feld[SJ][ZJ] = aendernGucken(nachbarnZaehlen());
          kara.move();
        }
        kara.turnRight();
        kara.move();
        kara.turnLeft();
        kara.move();
      }
      for (int ZJ = 0; ZJ < Zeile; ZJ++) {
        for (int SJ = 0; SJ<Spalte; SJ++) {
          aendernAusfuehren(Feld[SJ][ZJ]);
          Feld[SJ][ZJ] = false;
          kara.move();
        }
        kara.turnRight();
        kara.move();
        kara.turnLeft();
        kara.move();
      }
    }
  }


  public void aendernAusfuehren(boolean Wechsel) {
    if (Wechsel) {
      if (kara.onLeaf()) {
        kara.removeLeaf();
      }else{
        kara.putLeaf();
      }
    }
  }

  public boolean aendernGucken(int Nachbarn) {
    boolean Wechsel = false;
    if (Nachbarn == 2) {
      return Wechsel;
    }else{
      if (Nachbarn == 3) {
        if  (!kara.onLeaf()) {
          return (!Wechsel);
        }else{
          return Wechsel;
        }
      }else{
        if  (kara.onLeaf()) {
          return (!Wechsel);
        }else{
          return Wechsel;
        }
      }
    }
  }

  public int nachbarnZaehlen() {
    int Nachbarn = 0;
    Nachbarn = naechstesFeldAuswerten(Nachbarn);
    kara.turnRight();
    Nachbarn = naechstesFeldAuswerten(Nachbarn);
    kara.turnRight();
    Nachbarn = naechstesFeldAuswerten(Nachbarn);
    Nachbarn = naechstesFeldAuswerten(Nachbarn);
    kara.turnRight();
    Nachbarn = naechstesFeldAuswerten(Nachbarn);
    Nachbarn = naechstesFeldAuswerten(Nachbarn);
    kara.turnRight();
    Nachbarn = naechstesFeldAuswerten(Nachbarn);   
    Nachbarn = naechstesFeldAuswerten(Nachbarn);
    kara.turnRight();
    kara.move();
    kara.turnRight();
    kara.move();
    kara.turnRight();
    kara.turnRight();
    return Nachbarn;
  }

  public int naechstesFeldAuswerten (int i) {
    kara.move();
    if (kara.onLeaf()) {
      i = i+1;
    }
    return i;
  }

  public boolean[][] feldInitialisieren(int Zeile, int Spalte) {              //Z&S
    boolean[][] Feld = new boolean[Zeile][Spalte];              //Z&S
    for (int zt = 0; zt < Zeile; zt++) {
      for (int st = 0; st < Spalte; st++) {
        Feld[zt][st] = false;
      }
    }
  return Feld;
  }
}        