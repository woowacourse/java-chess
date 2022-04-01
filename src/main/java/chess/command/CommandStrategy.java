package chess.command;

import chess.ChessGame;

public interface CommandStrategy {
    void execute(final String command, final ChessGame chessGame);
}
