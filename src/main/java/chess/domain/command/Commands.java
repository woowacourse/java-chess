package chess.domain.command;

import java.util.Arrays;
import java.util.List;

public class Commands {
    private static final List<Command> commands;

    static {
        commands = Arrays.asList(
                new StartOnCommand(),
                new EndOnCommand(),
                new MoveOnCommand(),
                new StatusOnCommand());
    }

    public static Command findCommandByText(String anotherCommandText) {
        return commands.stream()
                .filter(command -> command.isMatch(anotherCommandText))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[ERROR] 잘못된 명령어 입니다."));
    }
}
