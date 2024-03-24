package view;

import domain.game.MovePosition;
import java.util.Arrays;

public enum Command {
    START("start", 0),
    END("end", 0),
    MOVE("move", 2);

    private final String name;
    private final int positionCount;

    Command(String name, int positionCount) {
        this.name = name;
        this.positionCount = positionCount;
    }

    public static Command from(MovePosition movePosition) {
        Command command = Arrays.stream(values())
                .filter(c -> c.name.equals(movePosition.commandName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));

        validatePositionCount(movePosition, command);
        return command;
    }

    private static void validatePositionCount(MovePosition movePosition, Command command) {
        if (command.positionCount != movePosition.count()) {
            throw new IllegalArgumentException("명령어의 길이가 올바르지 않습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
