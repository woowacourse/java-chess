package chess.domain.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Commands {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("end", new EndOnCommand());
        commands.put("start", new StartOnCommand());
        commands.put("move", new MoveOnCommand());
        commands.put("status", new StatusOnCommand());
    }

    public static Command findCommandByText(String commandText) {
        return Optional.ofNullable(commands.get(commandText))
                .orElseThrow(() ->
                        new IllegalArgumentException("[ERROR] 잘못된 명령어 입니다."));
    }
}
