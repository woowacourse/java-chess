package chess.controller.command.strategy;

import chess.controller.ChessState;
import chess.domain.game.ChessGame;

@FunctionalInterface
public interface StrategyCommand {

    ChessState execute(final ChessState state, final ChessGame chessGame);
}
