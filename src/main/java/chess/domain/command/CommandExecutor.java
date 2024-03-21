package chess.domain.command;


import chess.domain.CommandCondition;

@FunctionalInterface
public interface CommandExecutor {
    void execute(CommandCondition commandCondition);
}
