package controller;

import domain.chessgame.ChessGame;
import domain.piece.Color;
import view.OutputView;

import java.util.List;

public final class End extends GameStatus {

    End(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void playTurn(final List<String> inputs) {
        OutputView.printNotice("> 최종 결과");

        final ChessBoardDTO chessBoardDTO = ChessBoardDTO.from(chessGame.getChessBoard());

        final double whiteScore = chessGame.calculateScore(Color.WHITE);
        final double blackScore = chessGame.calculateScore(Color.BLACK);

        OutputView.printChessBoard(chessBoardDTO);
        OutputView.printScore(whiteScore, blackScore);
    }

    @Override
    public boolean isKeepGaming() {
        return false;
    }

}
