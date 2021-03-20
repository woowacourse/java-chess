package chess.domain.command;

import chess.domain.ChessGame;

public interface Command {
    String run(String input, ChessGame chessGame);

    boolean isSameCommand(String command);
}
