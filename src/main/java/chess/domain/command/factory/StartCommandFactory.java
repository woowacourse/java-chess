package chess.domain.command.factory;

import chess.domain.command.strategy.StrategyCommand;
import chess.domain.command.strategy.StartCommand;

import java.util.List;

public class StartCommandFactory implements ActionCommandFactory {

    @Override
    public StrategyCommand create(final List<String> inputs) {
        return StartCommand.create();
    }
}
