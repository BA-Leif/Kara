import javakara.JavaKaraProgram;

/* PROBLEME:

Skizze in Aufgabe ist falsch.... der Ursprung liegt hinter den "Armen"

Wenn in einer Rechnung nur ein Integer ist, dann ist das Ergebniss immer in Integer ausgegeben

*/

public class ApfelmannVer1_01 extends JavaKaraProgram {

  public void myProgram() {
    // hier kommt das Hauptprogramm hin, zB:
    tools.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");


//Zurücksetzen der Welt
    world.clearAll();
    world.setSize(200, 200);


//Definieren von relevanten Variablen
    float[] coordinateStart = new float[2];
    float[] coordinateTarget = new float[2];
    float[] OriginFloat = new float[2];
    boolean inArea = true;


//for-Schleife zur Betrachtung aller Koordinatenpunkte
    for (int xOrigin = 0; xOrigin < world.getSizeX(); xOrigin++) {
      for (int yOrigin = 0; yOrigin < world.getSizeY(); yOrigin++) {
  //unschöne Umwandlung der Ausgangskoordinaten (geht nicht in der Rechnung?)
        float xOriginFloat = xOrigin;
        float yOriginFloat = yOrigin;
  //Uebertagen des Koordinatensystems auf ein Koordinatensys mit einer Ausdehung vom max. 2.0 in alle Richtugnen
        OriginFloat[0] = (xOriginFloat - (world.getSizeX()/2)) / ((world.getSizeX()/2) /2);
        OriginFloat[1] = (yOriginFloat - (world.getSizeY()/2)) /  (world.getSizeY()/4);
  //Start des Weges auf den Ursprung setzen 
        coordinateStart[0] = OriginFloat[0];
        coordinateStart[1] = OriginFloat[1];

  //Überprüfung, ob man 100 Schritte gehen kann, ohne das Feld zu verlassen
        for (int i = 0; (i <= 99 && inArea) ; i++) {
    //Bestimmen der neuen Koordinaten
          coordinateTarget[0] = ((coordinateStart[0]*coordinateStart[0]) - (coordinateStart[1]*coordinateStart[1])) + OriginFloat[0];
          coordinateTarget[1] = (2 * coordinateStart[0] * coordinateStart[1]) + OriginFloat[1];
    //Überprüfen, ob er nächste Schritt im Feld liegt
          if (Math.abs(coordinateTarget[0]) <= 2    &&    Math.abs(coordinateTarget[1]) <= 2) {
            coordinateStart[0] = coordinateTarget[0];
            coordinateStart[1] = coordinateTarget[1];
    //Setzen eines Objektes, jenachdem, wieviele Schritte gemacht werden konnten
            if (i == 3) {
              world.setTree(xOrigin, yOrigin, true);
            } else if (i == 10) {
              world.setTree(xOrigin, yOrigin, false);
              world.setLeaf(xOrigin, yOrigin, true);
            } else if (i == 99) {
              world.setLeaf(xOrigin, yOrigin, false);
              world.setMushroom(xOrigin, yOrigin, true);
            }
    //Abbruch, sollte ein Schritt aus dem Feld gehen
          } else {
            inArea = false;
          }
        }
        inArea = true;
      }
    }
    world.setMushroom(world.getSizeX()/2, world.getSizeY()/2, false);
    world.setTree(world.getSizeX()/2, world.getSizeY()/2, true);
  }
}

        