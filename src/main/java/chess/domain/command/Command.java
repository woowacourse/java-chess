package chess.domain.command;

import chess.domain.ChessGame;

public interface Command {
    String run(ChessGame chessGame, String input);

    boolean isSameCommand(String command);
}
