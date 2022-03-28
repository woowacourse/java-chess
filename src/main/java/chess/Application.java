package chess;

import chess.domain.ChessGame;
import chess.view.Enter;
import chess.view.Input;
import chess.view.Output;

public final class Application {

    public static void main(String[] args) {
        Output.printChessGameStart();

        final var chessGame = new ChessGame();

        while (!chessGame.isExit()) {
            command(chessGame);
        }
    }

    private static void command(final ChessGame chessGame) {
        try {
            Command.execute(Input.inputCommand(new Enter()), chessGame);
        } catch (IllegalStateException | IllegalArgumentException exception) {
            Output.printExceptionMessage(exception.getMessage());
        }
    }
}
