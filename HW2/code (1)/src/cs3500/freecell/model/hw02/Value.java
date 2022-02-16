package cs3500.freecell.model.hw02;

/**
 * Represents the value of a Freecell card.
 */
public enum Value {
  ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"),
  NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K");

  private final String value;

  /**
   * Constructs a Value which can be one of 2-10, ACE, JACK, QUEEN, KING.
   */
  Value(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }
}

