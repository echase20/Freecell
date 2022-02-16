package cs3500.hw02;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for the SimpleFreecellModel class.
 */

public class TestSimpleFreecellModel {

  /**
   * These are used for testing.
   */
  SimpleFreecellModel model = new SimpleFreecellModel();


  @Test
  public void testGetNCIFP() {
    model.startGame(model.getDeck(), 4, 2, false);
    assertEquals(5, model.getNumCardsInCascadePile(1));
  }

  String roundRobin = "F1:" + "\n" +
      "F2:" + "\n" +
      "F3:" + "\n" +
      "F4:" + "\n" +
      "O1:" + "\n" +
      "O2:" + "\n" +
      "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠" + "\n" +
      "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠" + "\n" +
      "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠" + "\n" +
      "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠";

  String movedCard = "F1:" + "\n" +
      "F2:" + "\n" +
      "F3:" + "\n" +
      "F4:" + "\n" +
      "O1:" + " 10♠" + "\n" +
      "O2:" + "\n" +
      "O3:" + "\n" +
      "O4:" + "\n" +
      "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠" + "\n" +
      "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠" + "\n" +
      "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠" + "\n" +
      "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠";


  String movedCard1 = "F1:" + " A♠" + "\n" +
      "F2:" + "\n" +
      "F3:" + "\n" +
      "F4:" + "\n" +
      "O1:" + " K♠" + "\n" +
      "O2:" + " 9♠" + "\n" +
      "O3:" + " 5♠" + "\n" +
      "O4:" + "\n" +
      "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠" + "\n" +
      "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠" + "\n" +
      "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠" + "\n" +
      "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥, 10♥";

  String movedCard2 = "F1:" + " A♠" + "\n" +
      "F2:" + "\n" +
      "F3:" + "\n" +
      "F4:" + "\n" +
      "O1:" + " K♠" + "\n" +
      "O2:" + " 9♠" + "\n" +
      "O3:" + " 5♠" + "\n" +
      "O4:" + " 10♠" + "\n" +
      "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♥" + "\n" +
      "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠" + "\n" +
      "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠" + "\n" +
      "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥";

  String movedCard3 = "F1:" + "\n" +
      "F2:" + "\n" +
      "F3:" + "\n" +
      "F4:" + "\n" +
      "O1:" + "\n" +
      "O2:" + " K♠" + "\n" +
      "O3:" + "\n" +
      "O4:" + "\n" +
      "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠" + "\n" +
      "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠" + "\n" +
      "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠" + "\n" +
      "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠";

  @Test
  public void testgameStarted() {
    model.startGame(model.getDeck(), 4, 2, false);
    assertEquals(true, model.gameStarted);
  }

  //Test the getDeck Method to determine if it returns 52 cards.
  @Test
  public void testGetDeck() {
    assertEquals(52, model.getDeck().size());
  }

  @Test
  public void testGetDeckOrder() {
    assertEquals("a", model.getDeck());
  }

  @Test(expected = IllegalArgumentException.class)
  //fails to run if the deck has duplicates
  public void failWithDuplicates() {
    List<Card> x = model.getDeck();
    Card y = x.get(0);
    x.add(y);
    x.add(y);
    model.startGame(x, 4, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  //fails to run if the deck isn't a full deck
  public void failWithPartialDeck() {
    List<Card> x = model.getDeck();
    x.remove(0);
    model.startGame(x, 4, 4, false);
  }


  @Test(expected = IllegalArgumentException.class)
  //verify that startGame will throw an IllegalArgumentException
  // if the number of cascade piles is less than 4
  public void incorrectCascade() {
    model.startGame(model.getDeck(), 3, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  //verify that startGame will throw an IllegalArgumentException
  // if the number of open piles is less than 1
  public void incorrectOpen() {
    model.startGame(model.getDeck(), 4, 0, false);
  }

  @Test
  //verify that isGameOver returns true when the game is really over
  public void gameActuallyOver() {
    model.startGame(model.getDeck(), 52, 4, false);
    for (int i = 0; i < 13; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
      model.move(PileType.CASCADE, i + 13, 0, PileType.FOUNDATION, 1);
      model.move(PileType.CASCADE, i + 26, 0, PileType.FOUNDATION, 2);
      model.move(PileType.CASCADE, i + 39, 0, PileType.FOUNDATION, 3);
    }
    assertEquals(true, model.isGameOver());
  }


  @Test //Tests to see if the game is over when the game shouldn't be over
  public void gameOver() {
    model.startGame(model.getDeck(), 4, 4, false);
    assertEquals(false, model.isGameOver());
  }

  @Test(expected = IllegalArgumentException.class)
  //Tests to see if the game is over when the game shouldn't be over
  public void nullDeck() {
    model.startGame(null, 4, 4, false);
  }


  @Test //Tests a move from a cascade pile to an open pile
  public void testMove() {
    model.startGame(model.getDeck(), 4, 4, false);
    Card testCard = model.getCascadeCardAt(0, 12);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    Card testCard2 = model.getOpenCardAt(0);
    assertEquals(testCard, testCard2);
  }

  @Test //Tests a move from a cascade pile to a foundation pile
  public void testMove1() {
    model.startGame(model.getDeck(), 4, 4, false);
    Card testCard = model.getCascadeCardAt(3, 9);
    model.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 0);
    Card testCard2 = model.getFoundationCardAt(0, 0);
    assertEquals(testCard, testCard2);
  }

  @Test //Tests a move from a cascade pile to a cascade pile
  public void testMove2() {
    model.startGame(model.getDeck(), 52, 4, false);
    Card testCard = model.getCascadeCardAt(47, 0);
    model.move(PileType.CASCADE, 47, 0, PileType.CASCADE, 22);
    Card testCard2 = model.getCascadeCardAt(22, 1);
    assertEquals(testCard, testCard2);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Foundation to Open
  public void testMove3() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.FOUNDATION, 0, 12, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Foundation to
  // Cascade
  public void testMove4() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.FOUNDATION, 0, 12, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Foundation to
  //Foundation
  public void testMove5() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.FOUNDATION, 0, 12, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from empty Open to
  //Cascade
  public void testMove6() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 3);
  }

  @Test //Tests the move from an Open to Foundation
  public void testMove7() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 3, 9, PileType.OPEN, 3);
    Card testCard = model.getOpenCardAt(3);
    model.move(PileType.OPEN, 3, 0, PileType.FOUNDATION, 0);
    Card tesCard2 = model.getFoundationCardAt(0, 0);
    assertEquals(testCard, tesCard2);
  }

  @Test //Tests the move from Open to Open
  public void testMove8() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    Card testCard = model.getOpenCardAt(0);
    model.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
    Card testCard2 = model.getOpenCardAt(1);
    assertEquals(testCard, testCard2);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from a cascade
  //to Foundation where the card is not the correct card to put on the Foundation Pile
  public void testMove9() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Cascade to Cascade
  // where the card is not the right color to be placed
  public void testMove10() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Open to
  //Foundation where the card is not the right card to be placed on the foundation pile
  public void testMove11() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    model.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Cascade to Cascade
  //where the card being placed doesn't follow the cascade rules
  public void testMove12() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Open to Cascade
  //where the card being placed doesn't follow the cascade rules
  public void testMove13() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.OPEN, 0, 12, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class) //Tests the invalid move from Foundation
  //to Cascade where a card can't be moved once it is in the foundation pile
  public void testMove14() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.FOUNDATION, 0, 12, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  //tests to make sure you can only move the top card
  public void testMove15() {
    model.startGame(model.getDeck(), 4, 4, false);
    model.move(PileType.CASCADE, 0, 8, PileType.OPEN, 3);
  }


}