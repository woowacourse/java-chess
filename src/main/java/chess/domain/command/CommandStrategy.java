package chess.domain.command;

import chess.domain.ChessGame;

public interface CommandStrategy {
    void execute(final String command, final ChessGame chessGame);
}
