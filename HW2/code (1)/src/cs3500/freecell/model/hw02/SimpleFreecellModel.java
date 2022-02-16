package cs3500.freecell.model.hw02;



import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of the FreecellModel interface using the Card class.
  */

public class SimpleFreecellModel implements FreecellModel<Card> {

  public boolean gameStarted;
  private final ArrayList<Pile> openPiles;
  private final ArrayList<Pile> cascadePiles;
  private final ArrayList<Pile> foundationPiles;

  /**
   * Constructs a SimpleFreecellModel by initializing the three ArrayList of the three types of
   * Piles.
   */
  public SimpleFreecellModel() {
    this.gameStarted = false;
    this.openPiles = new ArrayList<>();
    this.cascadePiles = new ArrayList<>();
    this.foundationPiles = new ArrayList<>();
  }

  // gets a deck of 52 Freecell playing cards
  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<Card>();

    for (Suit suit : Suit.values()) {
      for (Value value : Value.values()) {
        deck.add(new Card(value, suit));
      }
    }
    return deck;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {
    if (validDeck(deck)) {
      if (numCascadePiles >= 4 && numOpenPiles >= 1) {
        if (shuffle) {
          Collections.shuffle(deck);
        }
        makePiles(numCascadePiles, numOpenPiles);
        dealCards(deck);
        this.gameStarted = true;
      } else {
        throw new IllegalArgumentException("Invalid Piles");
      }
    } else {
      throw new IllegalArgumentException("Invalid Deck");
    }
  }

  /**
   * initializes the piles for the start of the game.
   *
   * @param numCascadePiles number of cascade piles
   * @param numOpenPiles    number of open piles
   */
  private void makePiles(int numCascadePiles, int numOpenPiles) {
    for (int i = 0; i < numCascadePiles; i++) {
      cascadePiles.add(new Pile(PileType.CASCADE, new ArrayList<>()));
    }
    for (int i = 0; i < numOpenPiles; i++) {
      openPiles.add(new Pile(PileType.OPEN, new ArrayList<>()));
    }
    for (int i = 0; i < 4; i++) {
      foundationPiles.add(new Pile(PileType.FOUNDATION, new ArrayList<>()));
    }
  }

  /**
   * deals the deck in a round robin fashion.
   *
   * @param deck the deck of cards used
   */
  private void dealCards(List<Card> deck) {
    int i = 0;
    for (Card c : deck) {
      if (i == cascadePiles.size()) {
        i = 0;
      }
      cascadePiles.get(i).addCard(c);
      i++;
    }
  }

  /**
   * determines if the given deck is a valid deck of playing cards.
   *
   * @param deck the deck of cards being checked
   * @return whether or not the deck is valid
   */
  private boolean validDeck(List deck) {
    if (deck == null) {
      throw new IllegalArgumentException("Null Deck");
    }
    ArrayList<String> examplevalid = new ArrayList<>(Arrays.asList(
        "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣", "A♣",
        "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦", "A♦",
        "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥", "A♥",
        "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♠"));
    List<String> current = toLOString(deck);
    return current.size() == examplevalid.size() && current.containsAll(examplevalid);
  }

  /**
   * converts a list of cards into a list of strings.
   *
   * @param deck the deck of Cards being converted
   * @return the list of Strings from the list of Cards
   */
  private List<String> toLOString(List<Card> deck) {
    ArrayList<String> stringlist = new ArrayList<>();
    for (Card c : deck) {
      stringlist.add(c.toString());
    }
    return stringlist;
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    if (validMoveIndices(getPileType(source), getPileType(destination), pileNumber, destPileNumber)
        && cardExists(source, pileNumber, cardIndex)) {

      Pile sourcePile = getPileType(source).get(pileNumber);
      Pile destPile = getPileType(destination).get(destPileNumber);

      Card cardToMove = sourcePile.getCards().get(cardIndex);
      sourcePile.removeCard(cardToMove);

      if (validMove(sourcePile, destPile, cardToMove)) {
        destPile.addCard(cardToMove);

      } else {
        sourcePile.addCard(cardToMove);
        throw new IllegalArgumentException("Invalid Move");
      }

    } else {
      throw new IllegalArgumentException("Invalid Index");
    }
  }

  //Determines if the given card exists in the pile
  boolean cardExists(PileType type, int pileNumber, int cardIndex) {
    return getPileType(type).get(pileNumber).getCards().size() - 1 == cardIndex;
  }

  /**
   * determines which pile to retrieve.
   *
   * @param type the PileType of the pile to retrieve
   * @return the corresponding pile
   */
  protected ArrayList<Pile> getPileType(PileType type) {
    if (type == PileType.CASCADE) {
      return cascadePiles;
    } else if (type == PileType.OPEN) {
      return openPiles;
    } else {
      return foundationPiles;
    }
  }

  /**
   * checks to make sure that the indices for a move are valid.
   *
   * @param source         the list of piles the card is coming form
   * @param destination    the list of piles the card is going to
   * @param pileNumber     the indice of the pile in the source list
   * @param destPileNumber the indice of the pile in the destination list
   * @return
   */
  protected boolean validMoveIndices(ArrayList<Pile> source, ArrayList<Pile> destination,
      int pileNumber, int destPileNumber) {
    return pileNumber < source.size() && destPileNumber < destination.size() && pileNumber >= 0
        && destPileNumber >= 0;
  }

  //Determines if the Move is a Valid Move
  protected boolean validMove(Pile sourcePile, Pile destPile, Card toMove) {
    return destPile.validMovePile(sourcePile, toMove);
  }

  @Override
  public boolean isGameOver() {
    int fPileNum = 0;
    for (int i = 0; i < foundationPiles.size(); i++) {
      fPileNum += foundationPiles.get(i).getCards().size();
    }

    return (fPileNum == 52);
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (index < 0 || index > 3) {
      throw new IllegalArgumentException("index out of bounds");
    }
    if (!gameStarted) {
      throw new IllegalStateException("game hasn't started");
    }
    return foundationPiles.get(index).size();
  }


  @Override
  public int getNumCascadePiles() {
    if (!gameStarted) {
      return -1;
    }
    return cascadePiles.size();
  }

  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (index < 0 || index > getNumCascadePiles() - 1) {
      throw new IllegalArgumentException("index out of bounds");
    }
    if (!gameStarted) {
      throw new IllegalStateException("game hasn't started");
    }
    return cascadePiles.get(index).size();
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (index < 0 || index > getNumOpenPiles() - 1) {
      throw new IllegalArgumentException("index out of bounds");
    }
    if (!gameStarted) {
      throw new IllegalStateException("game hasn't started");
    }
    return openPiles.get(index).size();
  }

  @Override
  public int getNumOpenPiles() {
    if (!gameStarted) {
      return -1;
    }
    else {
      return openPiles.size();
    }

  }

  @Override
  public Card getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (pileIndex < 0 || pileIndex > 3 || cardIndex < 0 ||
        cardIndex > getNumCardsInFoundationPile(pileIndex) - 1) {
      throw new IllegalArgumentException("index out of bounds");
    }
    if (!gameStarted) {
      throw new IllegalStateException("game hasn't started");
    }
    return foundationPiles.get(pileIndex).get(cardIndex);
  }

  @Override
  public Card getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (pileIndex < 0 || pileIndex > getNumCascadePiles() - 1 || cardIndex < 0 ||
        cardIndex > getNumCardsInCascadePile(pileIndex) - 1) {
      throw new IllegalArgumentException("index out of bounds");
    }
    if (!gameStarted) {
      throw new IllegalStateException("game hasn't started");
    }
    return cascadePiles.get(pileIndex).get(cardIndex);
  }

  @Override
  public Card getOpenCardAt(int pileIndex) throws IllegalArgumentException, IllegalStateException {
    if (pileIndex < 0 || pileIndex > getNumOpenPiles() - 1) {
      throw new IllegalArgumentException("index out of bounds");
    }
    if (!gameStarted) {
      throw new IllegalStateException("game hasn't started");
    }
    if (openPiles.get(pileIndex).size() == 0) {
      return null;
    }
    return openPiles.get(pileIndex).get(0);
  }
}
