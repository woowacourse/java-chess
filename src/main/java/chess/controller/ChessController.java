package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.view.Enter;
import chess.view.Enterable;
import chess.view.Input;
import chess.view.Output;

public class ChessController {

    private static final Enterable enterable = new Enter();

    public void run() {
        Output.printChessGameStart();

        ChessGame chessGame = new ChessGame();

        while (chessGame.isRunning()) {
            command(chessGame);
        }
    }

    private void command(final ChessGame chessGame) {
        try {
            Command.execute(Input.inputCommand(enterable), chessGame);
            printBoard(chessGame);
        } catch (IllegalStateException | IllegalArgumentException exception) {
            Output.printExceptionMessage(exception.getMessage());
        }
    }

    private void printBoard(final ChessGame chessGame) {
        if (chessGame.isRunning()) {
            Output.printBoard(chessGame.getBoard());
        }
    }
}
