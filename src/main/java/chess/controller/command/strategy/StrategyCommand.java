package chess.controller.command.strategy;

import chess.controller.ChessState;
import chess.domain.Board;
import chess.domain.team.Team;

@FunctionalInterface
public interface StrategyCommand {

    ChessState execute(final ChessState state, final Board board, final Team team);
}
