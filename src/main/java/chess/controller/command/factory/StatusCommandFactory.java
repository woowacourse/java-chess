package chess.controller.command.factory;

import chess.controller.command.strategy.StatusCommand;
import chess.controller.command.strategy.StrategyCommand;

import java.util.List;

public class StatusCommandFactory implements ActionCommandFactory {

    @Override
    public StrategyCommand from(final List<String> inputs) {
        return StatusCommand.create();
    }
}
