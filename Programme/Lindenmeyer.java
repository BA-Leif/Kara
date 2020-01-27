import javakara.JavaKaraProgram;
        
public class Lindenmeyer extends JavaKaraProgram {

  public void myProgram() {
  tools.println("_-_-_-_-_-_-_-_-_START_-_-_-_-_-_-_-_-_-_-_");

    world.clearAll();
    world.setSize(800, 400);
    kara.setPosition(0, world.getSizeY()-1);

    int CommandLengthMax = 2000;                                 //31000 dauert etwa 5min

    String[] word = new String[20];
    word[0] = "FLFRFRFLF";
    for (int i = 1; i< 19; i++) {
      word[i] = "_";
    }
    int n_max = 0;
    
    for (int n = 1; n < 20; n++) {
      tools.println("Schritt: " + Integer.toString(n-1) + "; Anzahl der Befehle: " + Integer.toString(word[n-1].length()));
      tools.println(word[n-1]);
      if (word[n-1].length() <= CommandLengthMax) {
        for (int i = 0; i < word[n-1].length(); i++) {
          if (word[n-1].charAt(i) == 'F') {
            word[n] = word[n].concat(word[0]);                                                //NUR MÖGLICH, WENN word[n] ungleich leer
          } else if (word[n-1].charAt(i) == 'L') {
            word[n] = word[n].concat("L");
          } else if (word[n-1].charAt(i) == 'R') {
            //word[n] = String.join("", word[n], "R");                                        //erster Ansatz, geht nicht?
            word[n] = word[n].concat("R");
          }
        }
      } else {
        n_max= n-2;
        n = 20;
      }
    }
    tools.println("Infos zur zu zeichnenden Befehlskette:  " + Integer.toString(n_max) + " Schritte  und insgesamt " + Integer.toString(word[n_max].length()) + " Befehle.");

    for (int i = 1; i < (word[n_max].length())-1 ; i++) {
      if (word[n_max].charAt(i) == 'F') {
        kara.move();
        if (kara.onLeaf()) {
          kara.removeLeaf();
        } else {
          kara.putLeaf();
        }
      } else if (word[n_max].charAt(i) == 'R') {
        kara.turnRight();
      } else if (word[n_max].charAt(i) == 'L') {
        kara.turnLeft();
      }
      tools.sleep(0);
    }
  }
}

        