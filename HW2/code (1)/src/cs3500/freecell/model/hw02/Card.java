package cs3500.freecell.model.hw02;

/**
 * represents a playing card in the game of Freecell.
  */

public class Card {

  private final Value value;
  private final Suit suit;

  // constructs in instance of a playing card in the game of Freecell
  public Card(Value val, Suit suit) {
    this.value = val;
    this.suit = suit;
  }

  @Override
  public String toString() {
    return this.value.toString() + this.suit.toString();
  }

  /**
   * The Suit value of the card. For example, a A♣ card would return ♣.
   *
   * @return the Suit value of the card.
   */
  public Suit getSuit() {
    return this.suit;
  }

  /**
   * Returns the value of the card.
   *
   * @return the value of the card as defined by its ordinal.
   */
  public int getValue() {
    return this.value.ordinal();
  }

  /**
   * Gets the color of the Card. If it's a Spade or Diamonds it's Black, otherwise it's Red.
   *
   * @return the Color of the Card which can be Black or Red.
   */
  public String getColor() {
    if (this.suit.ordinal() == Suit.SPADES.ordinal()
        || this.suit.ordinal() == Suit.CLUBS.ordinal()) {
      return "Black";
    } else {
      return "Red";
    }
  }
}
