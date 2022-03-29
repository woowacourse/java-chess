package chess.controller;

import chess.domain.ChessGame;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();

        final ChessBoard chessBoard = ChessBoardFactory.createChessBoard();
        final ChessGame chessGame = new ChessGame(chessBoard);
        playTurn(chessGame);
    }

    private void playTurn(final ChessGame chessGame) {
        while (!chessGame.isEnd()) {
            final Command command = InputView.requestCommand();
            command.execute(chessGame);
        }
    }
}
