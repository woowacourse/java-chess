package controller;

import domain.chessgame.ChessGame;
import view.OutputView;

import java.util.List;

public final class Start extends GameStatus {

    Start(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void playTurn(final List<String> inputs) {
        final ChessBoardDTO chessBoardDTO = ChessBoardDTO.from(chessGame.getChessBoard());

        OutputView.printNotice("게임을 시작합니다.");
        OutputView.printChessBoard(chessBoardDTO);
    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }

}
