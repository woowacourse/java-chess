package controller.menu;

import model.ChessGame;
import view.OutputView;

public class End implements Menu {

    @Override
    public void play(ChessGame chessGame, OutputView outputView) {
        chessGame.end();
    }
}
