package chess.domain.command.strategy;

import chess.domain.Board;
import chess.domain.team.player.Player;

public class StartCommand implements StrategyCommand {

    private StartCommand() {
    }

    public static StartCommand create() {
        return new StartCommand();
    }

    @Override
    public void execute(final Board board, final Player player) {
    }
}
