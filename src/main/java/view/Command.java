package view;

import java.util.Arrays;
import java.util.List;

public enum Command {
    INIT,
    START,
    MOVE,
    END;

    public static Command from(List<String> input) {
        Command command = Arrays.stream(values())
                .filter(s -> input.get(0).equalsIgnoreCase(s.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("start, end, move만 입력가능합니다."));

        if (command == MOVE) {
            validateMoveCommands(input);
        }

        return command;
    }

    private static void validateMoveCommands(List<String> input) {
        if (input.size() != 3) {
            throw new IllegalArgumentException("source와 target이 필요합니다.");
        }
    }
}
