package chess.domain.command;

import java.util.Arrays;
import java.util.List;

public class Commands {
    private final List<Command> commands;

    public Commands(List<Command> commands) {
        this.commands = commands;
    }

    public static Commands validCommands() {
        return new Commands(Arrays.asList(
                new StartOnCommand(),
                new EndOnCommand(),
                new MoveOnCommand(),
                new StatusOnCommand()
        ));
    }

    public Command findCommandByText(String anotherCommandText) {
        return commands.stream()
                .filter(command -> command.isMatch(anotherCommandText))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[ERROR] 잘못된 명령어 입니다."));
    }
}
