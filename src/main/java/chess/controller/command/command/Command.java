package chess.controller.command.command;

import chess.controller.ChessState;
import chess.domain.game.ChessGame;

@FunctionalInterface
public interface Command {

    ChessState execute(final ChessState state, final ChessGame chessGame);
}
