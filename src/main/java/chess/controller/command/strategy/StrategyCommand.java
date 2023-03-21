package chess.controller.command.strategy;

import chess.controller.ChessState;
import chess.service.ChessService;

@FunctionalInterface
public interface StrategyCommand {

    ChessState execute(final ChessState state, final ChessService chessService);
}
