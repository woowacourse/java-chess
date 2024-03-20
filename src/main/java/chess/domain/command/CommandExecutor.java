package chess.domain.command;

import java.util.List;

@FunctionalInterface
public interface CommandExecutor {
    void execute(List<String> args);
}
