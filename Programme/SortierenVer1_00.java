import javakara.JavaKaraProgram;
        
/* 


 */
public class SortierenVer1_00 extends JavaKaraProgram {

  // hier können Sie eigene Methoden definieren

  public void myProgram() {

    int upperCount = 0;
    int lowerCount = 0;
    for (int y = 0; y < world.getSizeY(); y++) {
      lowerCount = _countLeaves(y); 
      if (lowerCount < upperCount) {
        _changeRows(y, upperCount, lowerCount);
        y = -1;
        upperCount = 0;
      } else {
      upperCount = lowerCount;
      }
    }
  }




  public int _countLeaves (int Row) {
    int counter = 0;
    boolean countEnd = false;
    for (int x = 0; x < world.getSizeX() && !countEnd; x++) {
      if (world.isLeaf(x, Row)) {
        counter++;
      }else {
        countEnd = true;
      }
    }
    tools.sleep(50);
    return counter;
  }


  public void _changeRows (int Row, int upperCount, int lowerCount) {
    for (int x = (lowerCount); x < upperCount; x++) {
      world.setLeaf(x, Row - 1, false);
    }
    tools.sleep(200);
    for (int x = (lowerCount); x < upperCount; x++) {
      world.setLeaf(x, Row, true);
    }
    tools.sleep(50);
  }
}

        