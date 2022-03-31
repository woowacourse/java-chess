package chess;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.position.Position;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    private static ChessController chessController;

    public static void main(final String[] args) {
        OutputView.printStartMessage();

        final ChessGame chessGame = new ChessGame(ChessBoardFactory.createChessBoard());
        chessController = new ChessController(chessGame);
        while (chessController.canPlay()) {
            playTurn();
        }
    }

    private static void playTurn() {
        try {
            final String input = InputView.getInput();
            final Command command = Command.from(input);
            runStartCommand(command);
            runMoveCommand(input, command);
            runStatusCommand(command);
            runEndCommand(command);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private static void runStartCommand(final Command command) {
        if (command.equals(Command.START)) {
            chessController.start();
        }
    }

    private static void runMoveCommand(final String input, final Command command) {
        if (command.equals(Command.MOVE)) {
            final Position from = Command.findFromPosition(input);
            final Position to = Command.findToPosition(input);
            chessController.move(from, to);
        }
    }

    private static void runStatusCommand(final Command command) {
        if (command.equals(Command.STATUS)) {
            chessController.status();
        }
    }

    private static void runEndCommand(final Command command) {
        if (command.equals(Command.END)) {
            chessController.end();
        }
    }
}
