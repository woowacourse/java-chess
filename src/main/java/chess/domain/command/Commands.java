package chess.domain.command;

import chess.exception.CommandFormatException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class Commands {

    private final Map<String, Command> commands;

    public Commands(final List<Command> commands) {
        this.commands = commands.stream()
                .collect(toMap(Command::getCommand, Function.identity()));
    }

    public Command getIfPresent(String input) {
        String commandInput = input.split(" ")[0];
        return Optional
                .ofNullable(commands.getOrDefault(commandInput, null))
                .orElseThrow(CommandFormatException::new);
    }

}
