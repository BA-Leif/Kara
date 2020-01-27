import javakara.JavaKaraProgram;
        
/* BEFEHLE:  kara.
 *   move()  turnRight()  turnLeft()
 *   putLeaf()  removeLeaf()
 *
 * SENSOREN: kara.
 *   treeFront()  treeLeft()  treeRight()
 *   mushroomFront()  onLeaf()
 */
public class ExkursZumThemaPunkte extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {
    world.clearAll();
    world.setSize(10,10);
    kara.setPosition(3,3);
    // hier kommt das Hauptprogramm hin, zB:
    tools.println("-----------------------------------------");
    java.awt.Point abc = new java.awt.Point(3,3);
    java.awt.Point PunktKara = new java.awt.Point();
    PunktKara = kara.getPosition();
    tools.println(abc.toString());
    tools.println(PunktKara.toString());
    if (PunktKara.equals(abc)) {
      tools.println("Hooray");
    }else{
      tools.println("BooHoo");
    }

/*    tools.println("---------");
    tools.println(PunktKara.toString());
    PunktKara.translate(-1,1);
    tools.println(PunktKara.toString());
    PunktKara.move(7,7);
    tools.println(PunktKara.toString());
*/
    tools.println("---------");
    java.awt.Point KaraFrage = new java.awt.Point(3,3);
    
   // if (PunktKara.equals(KaraFrage)) {
    if (!(kara.getPosition().equals(KaraFrage))) {
      tools.println("Hooray");
    }else{
      tools.println("BooHoo");
    }
    
    

  }
}

        