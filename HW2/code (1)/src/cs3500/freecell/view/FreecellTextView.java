package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModelState;
import java.io.IOException;

/**
 * A texual view of the current game of Freecell.
 */

public class FreecellTextView implements FreecellView {

  public final FreecellModelState<?> model;
  Appendable app;

  public FreecellTextView(FreecellModelState<?> model) {
    this.model = model;
  }

  public FreecellTextView(FreecellModelState<?> model, Appendable app) {
    this.model = model;
    this.app = app;
  }

  @Override
  public String toString() {

    String board = "";
    String nextLine = "\n";
    String colon = ":";
    String comma = ",";
    String space = " ";
    int numCascade = model.getNumCascadePiles();
    for (int i = 0; i < 4; i++) {
      String pileName = "F" + (i + 1);
      board += pileName += colon;
      for (int j = 0; j < model.getNumCardsInFoundationPile(i); j++) {
        board += space;
        board += model.getFoundationCardAt(i, j).toString();
        if (j != model.getNumCardsInFoundationPile(i) - 1) {
          board += comma;
        }

      }
      board += nextLine;
    }

    for (int i = 0; i < model.getNumOpenPiles(); i++) {
      String pileName = "O" + (i + 1);
      board += pileName += colon;
      for (int j = 0; j < model.getNumCardsInOpenPile(i); j++) {
        board += space;
        board += model.getOpenCardAt(i);
        if (j != model.getNumCardsInOpenPile(i) - 1) {
          board += comma;
        }
      }
      board += nextLine;

    }
    for (int i = 0; i < numCascade; i++) {
      String pileName = "C" + (i + 1);
      board += pileName += colon;
      for (int j = 0; j < model.getNumCardsInCascadePile(i); j++) {
        board += space;
        board += model.getCascadeCardAt(i, j).toString();
        if (j != model.getNumCardsInCascadePile(i) - 1) {
          board += comma;
        }
      }
      if (i != numCascade - 1) {
        board += nextLine;
      }
    }

    return board;
  }

  @Override
  public void renderBoard() throws IOException {
    this.app.append(this.model.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    return;
  }

}




