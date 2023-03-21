package chess.domain.command.factory;

import chess.domain.command.strategy.StrategyCommand;

import java.util.List;

public interface ActionCommandFactory {

    StrategyCommand create(List<String> inputs);
}
