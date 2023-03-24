package chess.controller.command.factory;

import chess.controller.command.strategy.StrategyCommand;

import java.util.List;

public interface ActionCommandFactory {

    StrategyCommand from(List<String> inputs);
}
