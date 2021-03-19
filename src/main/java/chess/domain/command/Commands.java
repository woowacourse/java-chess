package chess.domain.command;

import chess.exception.NoSuchCommandException;

import java.util.ArrayList;
import java.util.List;

public class Commands {
    private final List<Command> commands;

    public Commands(List<Command> commands) {
        this.commands = new ArrayList<>(commands);
    }

    public String execute(String input) {
        return commands.stream()
                .filter(command -> command.isSameCommand(input.toLowerCase()))
                .findAny()
                .orElseThrow(NoSuchCommandException::new)
                .run(input);
    }
}
