package chess.domain.command;

import chess.domain.ChessGame;

public interface Command {
    String run(ChessGame chessGame, CommandInput commandInput);

    boolean isSameCommand(CommandInput commandInput);
}
