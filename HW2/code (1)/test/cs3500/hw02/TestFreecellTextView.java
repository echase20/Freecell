package cs3500.hw02;

import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A testing class for the FreecellTextView Class.
 */
public class TestFreecellTextView {



  @Test
  public void TesttoString() {
    SimpleFreecellModel game = new SimpleFreecellModel();
    game.startGame(game.getDeck(), 4, 2, false);
    FreecellTextView gameview = new FreecellTextView(game);
    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠\n"
        + "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠\n"
        + "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠\n"
        + "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠", gameview.toString());
  }
}
