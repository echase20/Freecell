package cs3500.freecell.model.hw02;

/**
 * Represents the Suit of a Freecell Card.
 */
public enum Suit {
  CLUBS("♣"), DIAMONDS("♦"), HEARTS("♥"), SPADES("♠");

  private final String suit;

  /**
   * Constructs a Suit which can be a Clubs, Diamond, Hearts, or Spades.
   */
  Suit(String suit) {
    this.suit = suit;
  }

  @Override
  public String toString() {
    return this.suit;
  }
}
