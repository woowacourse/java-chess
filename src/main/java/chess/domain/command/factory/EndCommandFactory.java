package chess.domain.command.factory;

import chess.domain.command.strategy.StrategyCommand;
import chess.domain.command.strategy.EndCommand;

import java.util.List;

public class EndCommandFactory implements ActionCommandFactory {

    @Override
    public StrategyCommand create(final List<String> inputs) {
        return EndCommand.create();
    }
}
