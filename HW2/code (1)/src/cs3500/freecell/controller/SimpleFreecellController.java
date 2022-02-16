package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import cs3500.freecell.model.PileType;



/**
 * Represents the FreeCell Controller. Given certain inputs it appends the gamestate
 * appropriately an the given Appendable field which is to then be printed out later to the user.
 */
public class SimpleFreecellController implements FreecellController {

  private final FreecellModel<Card> model;
  private Appendable ap;
  private Scanner sc;
  private Command moveCommand;
  private boolean quit;
  private boolean gameStarted;


  /**
   * Constructs a Freecell Controller given a read.
   *
   * @param rd a Readable Object.
   * @param ap an Appendable Object.
   * @throws IllegalStateException when either rd or ap are null.
   */
  public SimpleFreecellController(FreecellModel<Card> model, Readable rd, Appendable ap) {
    try {
      Objects.requireNonNull(rd);
      Objects.requireNonNull(ap);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Cannot be null");
    }
    this.model = model;
    this.ap = ap;
    this.sc = new Scanner(rd);
    moveCommand = new Command();
    quit = false;
    gameStarted = false;
  }

  @Override
  public void playGame(List deck, int numCascades,
      int numOpens, boolean shuffle) {
    //Checks to see if the deck is null
    try {
      Objects.requireNonNull(deck);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Null Arguments");
    }

    //Starts the game
    startGame(this.model, deck, numCascades, numOpens, shuffle);

    //Runs the game
    if (gameStarted) {
      try {
        runGame(model);
      } catch (IOException e) {
        //Nothing to throw
      }
    }

    //End of Game
    if (!quit && gameStarted) {
      try {
        ap.append("Game over.").toString();
      } catch (IOException e) {
        //Nothing to throw
      }
    }
  }

  //Runs the Game and appropriately parses the input while the game hasn't finished yet.
  //@throws IOException
  private void runGame(FreecellModel<Card> model) throws IOException {
    while (!model.isGameOver()) {
      if (sc.hasNext()) {
        String parInput = sc.next();

        //Checks if user quit
        if (parInput.charAt(0) == 'q' || parInput.charAt(0) == 'Q') {
          ap.append("Game quit prematurely.");
          quit = true;
          return;
        }

        //Builds a command to be used for a move
        buildCommand(parInput);

        //attempts to make the move
        doCommand(model);
      }
    }
  }

  //Attempts to make a move
  //@throws IOException
  private void doCommand(FreecellModel<Card> model) throws IOException {
    if (moveCommand.validCommand()) {
      try {
        moveCommand.move(model);
        ap.append(new FreecellTextView(model) + "\n");
      } catch (IllegalArgumentException e) {
        ap.append("Invalid move. Try again." + "\n");
      }
      moveCommand = new Command();
    }
  }

  //Builds a command by using the Command class and parsing the input.
  //@throws IOException
  private void buildCommand(String parInput) throws IOException {
    switch (moveCommand.size()) {
      case 0:
        if (validPile(parInput.charAt(0))
            && validInt(parInput.substring(1))) {
          moveCommand.setSource(getPile(parInput.charAt(0)))
              .setSourceNum(getNum(parInput.substring(1)));
        } else {
          ap.append("Re-enter Source Type and Pile Number" + "\n");
        }
        break;
      case 2:
        if (validInt(parInput.substring(0))) {
          moveCommand.setCardIndex(getNum(parInput.substring(0)));
        } else {
          ap.append("Re-enter Card Index" + "\n");
        }
        break;
      case 3:
        if (validPile(parInput.charAt(0)) && validInt(parInput.substring(1))) {
          moveCommand
              .setDest(getPile(parInput.charAt(0)))
              .setDestNum(getNum(parInput.substring(1)));
        } else {
          ap.append("Re-enter Destination Type and Pile Number" + "\n");
        }
        break;
      default:
        break;
    }
  }

  //Determines if the given integer is a valid integer.
  private boolean validInt(String s) {
    int x;
    try {
      x = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return x > 0;
  }

  //Determines if the given character is a valid piletype
  private boolean validPile(char parInput) {
    return (parInput == 'c') || (parInput == 'o')
        || (parInput == 'f') || (parInput == 'C')
        || (parInput == 'O') || (parInput == 'F');
  }

  //Converts a string to an integer
  private int getNum(String s) {
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  //Starts the game by calling upon start game in the model
  private void startGame(FreecellModel<Card> model, List deck,
      int numCascades, int numOpens, boolean shuffle) {
    try {
      try {
        model.startGame(deck, numCascades, numOpens, shuffle);
        gameStarted = true;
        ap.append(new FreecellTextView(model) + "\n");
      } catch (IllegalArgumentException e) {
        ap.append("Could not start game.");
      }
    } catch (IOException e) {
      //Nothing to throw
    }
  }

  //returns the piletype that the character represents
  private PileType getPile(char c) {
    if (c == 'C' || c == 'c') {
      return PileType.CASCADE;
    } else if (c == 'O' || c == 'o') {
      return PileType.OPEN;
    } else if (c == 'F' || c == 'f') {
      return PileType.FOUNDATION;
    } else {
      return null;
    }
  }

  /**
   * creates a new SimpleFreecellController and plays a game.
   * @param args the game of Freecell code
   */
  public static void main(String[] args) {
    SimpleFreecellController game = new SimpleFreecellController(new SimpleFreecellModel(),
        new InputStreamReader(System.in), System.out);
    game.playGame(game.model.getDeck(), 4, 2, false);

  }
}