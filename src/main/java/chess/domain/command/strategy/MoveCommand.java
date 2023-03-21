package chess.domain.command.strategy;

import chess.domain.Board;
import chess.domain.position.Position;
import chess.domain.team.player.Player;

import java.util.List;

public class MoveCommand implements StrategyCommand {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final Position source;
    private final Position target;

    private MoveCommand(final Position source, final Position target) {
        this.source = source;
        this.target = target;
    }

    public static MoveCommand from(final List<String> inputs) {
        final Position source = Position.from(inputs.get(SOURCE_INDEX));
        final Position target = Position.from(inputs.get(TARGET_INDEX));

        return new MoveCommand(source, target);
    }

    @Override
    public void execute(final Board board, final Player player) {
        board.move(source, target, player);
    }
}
