package techcourse.fp.chess.controller;

import java.util.List;

public enum Command {
    START,
    LOAD,
    END,
    SAVE,
    MOVE,
    STATUS,
    EMPTY;

    public static Command createInitCommand(String input) {
        final List<Command> rightCommands = List.of(START, END, LOAD);
        
        return createSpecficCommand(input, rightCommands);
    }

    private static Command createSpecficCommand(final String input, final List<Command> rightCommands) {
        return rightCommands.stream()
                .filter(command -> command.name().equalsIgnoreCase(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어를 입력하셨습니다."));
    }

    public static Command createInPlayCommand(String input) {
        final List<Command> rightCommands = List.of(MOVE, END, STATUS, SAVE);

        return createSpecficCommand(input, rightCommands);
    }
}
