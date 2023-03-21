package chess.domain.command.factory;

import chess.domain.command.strategy.StrategyCommand;
import chess.domain.command.strategy.MoveCommand;

import java.util.List;

public class MoveCommandFactory implements ActionCommandFactory {

    @Override
    public StrategyCommand create(final List<String> inputs) {
        return MoveCommand.from(inputs);
    }
}
