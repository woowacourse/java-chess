package chess.controller.command.factory;

import chess.controller.command.strategy.StartCommand;
import chess.controller.command.strategy.StrategyCommand;

import java.util.List;

public class StartCommandFactory implements ActionCommandFactory {

    @Override
    public StrategyCommand createCommand(final List<String> inputs) {
        return StartCommand.create();
    }
}
