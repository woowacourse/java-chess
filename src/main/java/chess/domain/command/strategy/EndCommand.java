package chess.domain.command.strategy;

import chess.domain.Board;
import chess.domain.team.player.Player;

public class EndCommand implements StrategyCommand {

    private EndCommand() {
    }

    public static EndCommand create() {
        return new EndCommand();
    }

    @Override
    public void execute(final Board board, final Player player) {
    }
}
