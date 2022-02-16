package cs3500.hw02;


import cs3500.freecell.controller.Command;
import cs3500.freecell.model.PileType;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Class for testing the Command Class.
 */
public class TestCommand {

  Command x = new Command();

  @Test
  //tests to see if the method size works
  public void testSizeandSetMethods() {
    x.setSource(PileType.FOUNDATION)
        .setSourceNum(2)
        .setCardIndex(2)
        .setDest(PileType.CASCADE)
        .setDestNum(2);
    assertEquals(x.size(), 5);
  }

  @Test
  //tests the setSource method
  public void testSetSource() {
    x.setSource(PileType.CASCADE);
    assertEquals(x.getSource(), PileType.CASCADE);
  }

  @Test
  //tests the setSourceNum method
  public void testSetSourceNum() {
    x.setSourceNum(5);
    assertEquals(x.getSourceNum(), 4);
  }

  @Test
  //tests the setCardIndex method
  public void testSetCardIndex() {
    x.setCardIndex(5);
    assertEquals(x.getCardIndex(), 4);
  }

  @Test
  //tests the setDest method
  public void testSetDest() {
    x.setDest(PileType.CASCADE);
    assertEquals(x.getDest(), PileType.CASCADE);
  }

  @Test
  //tests the setDestNum method
  public void testSetDestNum() {
    x.setDestNum(4);
    assertEquals(x.getdDestNum(), 3);
  }


  @Test(expected = IllegalArgumentException.class)
  //tests to see if it throws an exception if the constructor is violated
  public void testInvalidConstructor() {
    Command y = new Command(PileType.CASCADE, 0, -1, PileType.FOUNDATION,
        0);
  }

}
