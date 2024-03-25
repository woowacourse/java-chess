package chess.view.mapper;

import chess.domain.Command;

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
        Command command = toCommand(input);
        if (command.isMove()) {
            throw new IllegalArgumentException("게임 시작을 안 했습니다.");
        }
        return command;
    }

    public static Command toMoveOrEndCommand(String input) {
        Command command = toCommand(input);
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 게임 시작을 했습니다.");
        }
        return command;
    }

    private static Command toCommand(String input) {
        return Arrays.stream(values())
                .filter(it -> it.input.equals(input))
                .findFirst()
                .map(it -> it.command)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }
}
