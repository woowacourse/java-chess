package chess.view;

import chess.controller.command.Command;
import chess.controller.command.EndCommand;
import chess.controller.command.MoveCommand;
import chess.controller.command.StartCommand;

import java.util.Arrays;
import java.util.function.Function;

public enum CommandMapper {
    START("start", StartCommand::of),
    END("end", EndCommand::of),
    MOVE("move", MoveCommand::of);

    private static final String ARGUMENT_SEPARATOR = " ";

    private final String code;
    private final Function<String, Command> mapper;

    CommandMapper(String code, Function<String, Command> mapper) {
        this.code = code;
        this.mapper = mapper;
    }

    public static Command from(String input) {
        return Arrays.stream(values())
                .filter(command -> command.commandStartsWithCode(input))
                .findAny()
                .map(command -> command.mapper.apply(input))
                .orElseThrow(() -> new IllegalArgumentException(input + "은 존재하지 않는 커멘드 입니다."));
    }

    private boolean commandStartsWithCode(String input) {
        String inputCommand = input.split(ARGUMENT_SEPARATOR)[0];
        return inputCommand.equals(code);
    }

    public String getCode() {
        return code;
    }
}
