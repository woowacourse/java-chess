package view.mapper;

import domain.Command;

import java.util.Arrays;

public enum CommandMapper {

    START("start", Command.START),
    END("end", Command.END),
    MOVE("move", Command.MOVE),
    ;

    private final String input;
    private final Command command;

    CommandMapper(String input, Command command) {
        this.input = input;
        this.command = command;
    }

    public static Command toStartOrEndCommand(String input) {
        Command command = from(input);
        if (command == Command.MOVE) {
            throw new IllegalArgumentException("시작 명령은 start 혹은 end만 입력 가능합니다.");
        }
        return command;
    }

    public static Command from(String input) {
        return Arrays.stream(values())
                .filter(command -> command.input.equals(input))
                .findFirst()
                .map(it -> it.command)
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Command toMoveCommand(String input) {
        Command command = from(input);
        if (command != Command.MOVE) {
            throw new IllegalArgumentException("move 명령어를 입력해야 합니다.");
        }
        return command;
    }
}
