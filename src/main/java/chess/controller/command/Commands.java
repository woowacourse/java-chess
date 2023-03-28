package chess.controller.command;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Commands {

    private final Map<String, Command> commands;

    public Commands(List<Command> commands) {
        this.commands = commands.stream()
                .collect(Collectors.toMap(Command::getCommand, Function.identity()));
    }

    public Command findCommand(String input) {
        return commands.getOrDefault(input, new IllegalCommand());
    }
}
