package cs3500.freecell.model.hw02;

import cs3500.freecell.model.PileType;
import java.util.ArrayList;

/**
 *  Represents a Pile in the game Freecell. A pile has a type
 *  and a list of cards.
 */
public class Pile {

  private final PileType type;
  private final ArrayList<Card> cards;

  /**
   * Makes a pile.
   *
   * @param type  represents the PileType
   * @param cards represents the cards in the Pile
   */

  public Pile(PileType type, ArrayList<Card> cards) {
    this.type = type;
    this.cards = cards;
  }

  /**
   * Returns the ArrayList of cards within the Pile.
   *
   * @return ArrayList
   */
  public ArrayList<Card> getCards() {
    return this.cards;
  }

  /**
   * returns the PileType of the Pile.
   *
   * @return PileType
   */
  public PileType getType() {
    return this.type;
  }

  /**
   * Adds the given card to the Cards field.
   *
   * @param c Represents a Card
   */
  public void addCard(Card c) {
    this.cards.add(c);
  }

  /**
   * Removes the given card from the Cards field.
   *
   * @param c Represents a Card
   */
  public void removeCard(Card c) {
    this.cards.remove(c);
  }


  /**
   * Helper method for move that helps determine if the move is a valid move.
   *
   * @param sourcePile Represents the Source Pile
   * @param toMove     represents the Card to Move
   * @return a boolean that is determined by whether or not a move is valid.
   */
  public boolean validMovePile(Pile sourcePile, Card toMove) {
    boolean emptyDest = this.cards.isEmpty();

    if (sourcePile.getType() == PileType.FOUNDATION ||
        (this.type == PileType.OPEN && (!emptyDest))) {
      return false;
    }

    if (this.type == PileType.FOUNDATION) {
      if (!emptyDest) {
        Card destCard = this.cards.get(this.cards.size() - 1);
        boolean sameSuit = destCard.getSuit() == toMove.getSuit();
        boolean oneMore = destCard.getValue() + 1 == toMove.getValue();
        return sameSuit && oneMore;
      } else {
        boolean isAce = toMove.getValue() == Value.ACE.ordinal();
        return isAce;
      }
    }

    if (this.type == PileType.CASCADE && !emptyDest) {
      Card destCard = this.cards.get(this.cards.size() - 1);
      boolean differentColor = !destCard.getColor().equals(toMove.getColor());
      boolean smallerVal = destCard.getValue() == toMove.getValue() + 1;
      return differentColor && smallerVal;
    }

    return true;

  }


  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    for (int k = 0; k < this.getCards().size(); k++) {
      if (k == this.getCards().size() - 1) {
        res.append(" " + this.getCards().get(k));
      } else {
        res.append(" " + this.getCards().get(k) + ",");
      }
    }
    return res.toString();
  }

  public int size() {
    return cards.size();
  }

  public Card get(int cardIndex) {
    return cards.get(cardIndex);
  }
}


