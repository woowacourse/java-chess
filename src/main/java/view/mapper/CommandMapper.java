package view.mapper;

import domain.Command;

import java.util.Arrays;

public enum CommandMapper {

    START("start", Command.START),
    END("end", Command.END),
    ;

    private final String input;
    private final Command command;

    CommandMapper(String input, Command command) {
        this.input = input;
        this.command = command;
    }

    public static Command from(String input) {
        return Arrays.stream(values())
                .filter(command -> command.input.equals(input))
                .findFirst()
                .map(it -> it.command)
                .orElseThrow(IllegalArgumentException::new);
    }
}
