package chess;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.position.Position;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {
    public static void main(final String[] args) {
        OutputView.printStartMessage();

        final ChessGame chessGame = new ChessGame(ChessBoardFactory.createChessBoard());
        final ChessController chessController = new ChessController(chessGame);
        run(chessController);
    }

    private static void run(final ChessController chessController) {
        while (chessController.canPlay()) {
            try {
                final String input = InputView.getInput();
                final Command command = Command.from(input);
                if (command.equals(Command.START)) {
                    chessController.start();
                }
                if (command.equals(Command.MOVE)) {
                    final Position from = Command.findFromPosition(input);
                    final Position to = Command.findToPosition(input);
                    chessController.move(from, to);
                }
                if (command.equals(Command.STATUS)) {
                    chessController.status();
                }
                if (command.equals(Command.END)) {
                    chessController.end();
                }
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
