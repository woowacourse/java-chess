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
        OutputView.printNotice("게임을 시작합니다.");
        final ChessBoardDTO chessBoardDTO = ChessBoardDTO.from(chessGame.getChessBoard());
        OutputView.printChessBoard(chessBoardDTO);
    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }

}
