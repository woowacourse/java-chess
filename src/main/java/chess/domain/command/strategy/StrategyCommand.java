package chess.domain.command.strategy;

import chess.domain.Board;
import chess.domain.team.player.Player;

@FunctionalInterface
public interface StrategyCommand {

    void execute(final Board board, final Player player);
}
