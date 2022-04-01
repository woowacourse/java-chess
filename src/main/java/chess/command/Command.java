package chess.command;

import chess.domain.ChessGame;

public interface Command {

    void execute(final ChessGame chessGame);
}
