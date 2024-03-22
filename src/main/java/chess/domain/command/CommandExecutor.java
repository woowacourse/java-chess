package chess.domain.command;


@FunctionalInterface
public interface CommandExecutor {

    void execute(CommandCondition commandCondition);
}
