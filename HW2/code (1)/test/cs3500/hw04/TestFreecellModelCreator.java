package cs3500.hw04;

import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.MultiMoveFreecellModel;
import org.junit.Test;

import cs3500.freecell.model.FreecellModel;

import static junit.framework.TestCase.assertEquals;

/**
 * Class for testing the FreecellModelCreator.
 */
public class TestFreecellModelCreator {

  FreecellModelCreator model;


  @Test
  //Tests the create method to makes sure it creates a Model of type multi
  public void testCreateMulti() {
    assertEquals(true,
        (FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE)
            instanceof MultiMoveFreecellModel));
  }

  @Test
  //Tests to the create method to make sure it creates a Model of type single
  public void testCreateSingle() {
    assertEquals(true, (
        FreecellModelCreator.create(FreecellModelCreator.GameType.SINGLEMOVE)
            instanceof FreecellModel));
  }


  @Test(expected = IllegalArgumentException.class)
  //Tests the create method with a null GameType
  public void testCreateNull() {
    assertEquals(true, (
        FreecellModelCreator.create(null)
            instanceof FreecellModel));
  }

}

