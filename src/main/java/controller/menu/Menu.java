package controller.menu;

import model.ChessGame;
import view.OutputView;

public interface Menu {

    void play(ChessGame chessGame, OutputView outputView);
}
