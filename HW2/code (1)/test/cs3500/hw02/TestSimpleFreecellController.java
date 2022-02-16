package cs3500.hw02;

import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the SimpleFreecellController class.
 */
public class TestSimpleFreecellController {

  SimpleFreecellModel model = new SimpleFreecellModel();
  StringBuffer ap = new StringBuffer();


  @Test
  //Tests to see if the game quits after an input of Q
  public void testQuit() {
    Reader r = new StringReader("Q");
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 8, 4, false);
    assertEquals(new FreecellTextView(model) + "\n" + "Game quit prematurely.", ap.toString());
  }

  @Test
  //Tests to see if Shuffle works from the controller
  public void testShuffleFromController() {
    Reader r = new StringReader("Q");
    Reader p = new StringReader("Q");
    StringBuffer ap2 = new StringBuffer();
    SimpleFreecellController x = new SimpleFreecellController(model, r, ap);
    SimpleFreecellController y = new SimpleFreecellController(model, p, ap2);
    x.playGame(model.getDeck(), 8, 4, true);
    y.playGame(model.getDeck(), 8, 4, false);
    assertNotEquals(ap.toString(), ap2.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  //Tests to see if it throws an exception for a null deck
  public void testNullDeck() {
    Reader r = new StringReader("Q");
    new SimpleFreecellController(model, r, ap).playGame(null, 8, 4, false);
  }

  @Test
  //Tests to see if it doesn't start game with a partial deck
  public void testPartialDeck() {
    Reader r = new StringReader("Q");
    List x = model.getDeck();
    x.remove(2);
    x.remove(1);
    new SimpleFreecellController(model, r, ap).playGame(x, 8, 4, false);
    assertEquals("Could not start game.", ap.toString());
  }

  @Test
  //Tests to see if it doesn't start game with a partial deck
  public void testDuplicatesDeck() {
    Reader r = new StringReader("Q");
    List x = model.getDeck();
    Object dup = x.get(1);
    x.add(dup);
    x.add(dup);
    new SimpleFreecellController(model, r, ap).playGame(x, 8, 4, false);
    assertEquals("Could not start game.", ap.toString());
  }


  @Test(expected = IllegalStateException.class)
  //tests to see if throws an exception if SimpleFreecellController isn't properly initialized
  public void testObjectNotInitialized() {
    Reader r = new StringReader(" Q");
    new SimpleFreecellController(model, null, ap);
  }

  @Test(expected = IllegalStateException.class)
  //tests to see if throws an exception if SimpleFreecellController isn't properly initialized
  public void testObjectNotInitialized1() {
    Reader r = new StringReader(" Q");
    new SimpleFreecellController(model, r, null);
  }

  @Test
  //tests to see if it doesn't start game if given an invalid start game with numOpens < 1
  public void cannotStartGame() {
    Reader r = new StringReader("Q");
    new SimpleFreecellController(model, r, ap)
        .playGame(model.getDeck(), 8, 0, false);
    assertEquals("Could not start game.", ap.toString());
  }

  @Test
  //test for an empty deck
  public void cannotStartGame0() {
    Reader r = new StringReader("Q");
    new SimpleFreecellController(model, r, ap)
        .playGame(new ArrayList(), 8, 0, false);
    assertEquals("Could not start game.", ap.toString());
  }


  @Test
  //tests to see if it doesn't start game if given an invalid start game with numCascades < 4
  public void cannotStartGame1() {
    Reader r = new StringReader("Q");
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 3, 4, false);
    assertEquals("Could not start game.", ap.toString());
  }


  @Test
  //Tests to see if it still quits if Q is preceded by an empty space
  public void testEmptyInput() {
    Reader r = new StringReader(" Q");
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 8, 4, false);
    assertEquals(new FreecellTextView(model) + "\n" + "Game quit prematurely.", ap.toString());
  }

  @Test
  //Tests to see if it still quits with a lowercase letter
  public void testQuitLowerCase() {
    Reader r = new StringReader("q");
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 8, 4, false);
    assertEquals(new FreecellTextView(model) + "\n" + "Game quit prematurely.", ap.toString());
  }


  @Test
  //Tests to see if it responds correctly to an invalid move
  public void testInvalidMove() {
    Reader r = new StringReader("C1 7 F1 Q");
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 8, 4, false);
    assertEquals(new FreecellTextView(model) + "\n" + "Invalid move. Try again." + "\n" + "Game " +
        "quit prematurely.", ap.toString());
  }


  @Test
  //test a basic invalid cardindex input
  public void testInvalidInput() {
    Reader r = new StringReader("C1 A Q");
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 8, 4, false);
    assertEquals(new FreecellTextView(model) + "\n" + "Re-enter Card Index" + "\n" + "Game " +
        "quit prematurely.", ap.toString());
  }

  @Test
  //tests if the card index that is too big is an invalid move
  public void testInvalidCardIndex() {
    Reader r = new StringReader("C1 100 O1 Q");
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 8, 4, false);
    assertEquals(new FreecellTextView(model) + "\n" + "Invalid move. Try again." + "\n" + "Game " +
        "quit prematurely.", ap.toString());
  }

  @Test
  //tests if the card index that is too big is an invalid move
  public void testInvalidCardIndex1() {
    Reader r = new StringReader("C1 -1 1 Q");
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 8, 4, false);
    assertEquals(new FreecellTextView(model) + "\n" + "Re-enter Card Index" + "\n" + "Game " +
        "quit prematurely.", ap.toString());
  }


  @Test
  //tests to see if it asks the user for a new source pile/source num if given an invalid one
  public void testInvalidSourceDest() {
    Reader r = new StringReader("CA C1 7 Z1 Q");
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 8, 4, false);
    assertEquals(new FreecellTextView(model) + "\n" + "Re-enter Source Type and Pile Number"
        + "\n" + "Re-enter Destination Type and Pile Number" + "\n" + "Game " +
        "quit prematurely.", ap.toString());
  }


  @Test
  //test a move from foundation
  public void testMove1() {
    Reader r = new StringReader("F1 7 O1 Q");
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 8, 4, false);
    assertEquals(new FreecellTextView(model) + "\n" + "Invalid move. Try again." + "\n" + "Game " +
        "quit prematurely.", ap.toString());
  }

  @Test
  //test a move from an open pile
  public void testMove2() {
    Reader r = new StringReader("O1 7 O1 Q");
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 8, 4, false);
    assertEquals(new FreecellTextView(model) + "\n" + "Invalid move. Try again." + "\n" + "Game " +
        "quit prematurely.", ap.toString());
  }


  @Test
  //test a move to foundation
  public void testMove4() {
    Reader r = new StringReader("F1 7 C1 Q");
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 8, 4, false);
    assertEquals(new FreecellTextView(model) + "\n" + "Invalid move. Try again." + "\n" + "Game " +
        "quit prematurely.", ap.toString());
  }

  @Test
  //test a move to an open pile
  public void testMove5() {
    Reader r = new StringReader("O1 7 O1 Q");
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 8, 4, false);
    assertEquals(new FreecellTextView(model) + "\n" + "Invalid move. Try again." + "\n" + "Game " +
        "quit prematurely.", ap.toString());
  }


  @Test
  //test to see if the game actually ends when
  public void testGameOver() {
    String f1 = "";
    String f2 = "";
    String f3 = "";
    String f4 = "";

    for (int i = 1; i < 14; i++) {
      f1 += "C" + i + " 1 " + "F1 ";
      f2 += "C" + (i + 13) + " 1 " + "F2 ";
      f3 += "C" + (i + 26) + " 1 " + "F3 ";
      f4 += "C" + (i + 39) + " 1 " + "F4 ";
    }

    Reader r = new StringReader(f1 + f2 + f3 + f4);
    new SimpleFreecellController(model, r, ap).playGame(model.getDeck(), 52, 4, false);
    assertEquals(true, model.isGameOver());
    assertEquals("Game over.", ap.substring(ap.length() - 10));
  }

}