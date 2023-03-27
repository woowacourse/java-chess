package chess.controller.command.factory;

import chess.controller.command.strategy.MoveCommand;
import chess.controller.command.strategy.StrategyCommand;

import java.util.List;

public class MoveCommandFactory implements ActionCommandFactory {

    @Override
    public StrategyCommand createCommand(final List<String> inputs) {
        return MoveCommand.from(inputs);
    }
}
