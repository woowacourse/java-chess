package chess.controller;

import chess.controller.command.Command;
import chess.domain.ChessGame;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();

        final ChessBoard chessBoard = ChessBoardFactory.createChessBoard();
        final ChessGame chessGame = new ChessGame(chessBoard);
        while (chessGame.canPlay()) {
            playTurn(chessGame);
        }
    }

    private void playTurn(final ChessGame chessGame) {
        try {
            final Command command = InputView.requestCommand();
            command.execute(chessGame);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }
}
