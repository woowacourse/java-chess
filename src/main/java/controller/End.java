package controller;

import domain.chessgame.ChessGame;
import view.OutputView;

import java.util.List;

public class End extends GameStatus {

    protected End(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void playTurn(final List<String> inputs) {
        OutputView.printNotice("> 최종 결과");
    }

    @Override
    public boolean isKeepGaming() {
        return false;
    }

}
