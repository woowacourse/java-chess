package controller;

import domain.chessgame.ChessGame;
import view.OutputView;

import java.util.List;

public final class End extends GameStatus {

    End(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public ChessBoardDTO playTurn(final List<String> inputs) {
        OutputView.printNotice("> 최종 결과");

        return ChessBoardDTO.from(chessGame.getChessBoard());
    }

    @Override
    public boolean isKeepGaming() {
        return false;
    }

}
