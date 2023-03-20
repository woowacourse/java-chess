package controller;

import domain.chessgame.ChessGame;
import view.OutputView;

import java.util.List;

public final class Start extends GameStatus {

    Start(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public ChessBoardDTO playTurn(final List<String> inputs) {
        OutputView.printNotice("게임을 시작합니다.");

        return ChessBoardDTO.from(chessGame.getChessBoard());
    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }

}
