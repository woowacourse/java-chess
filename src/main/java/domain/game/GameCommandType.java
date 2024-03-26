package domain.game;

import domain.board.Position;

import java.util.Arrays;
import java.util.function.Function;

public enum GameCommandType {
    START("start", command -> new Start()),
    MOVE("move", GameCommandType::toMove),
    END("end", command -> new End());

    private static final int COMMAND_INDEX = 0;
    private static final int BEFORE_POSITION = 1;
    private static final int AFTER_POSITION = 2;
    private static final int COMMAND_WITH_POSITIONS_SIZE = 3;

    private final String value;
    private final Function<String[], GameCommand> gameCommand;

    GameCommandType(final String value, final Function<String[], GameCommand> gameCommand) {
        this.value = value;
        this.gameCommand = gameCommand;
    }

    public static GameCommand of(final String[] values) {
        return Arrays.stream(GameCommandType.values())
                .filter(it -> it.value.equalsIgnoreCase(values[COMMAND_INDEX]))
                .map(it -> it.gameCommand.apply(values))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 명령어입니다!"));
    }

    private static Move toMove(final String[] values) {
        validatePositionValue(values);

        Position source = Position.of(values[BEFORE_POSITION]);
        Position destination = Position.of(values[AFTER_POSITION]);

        return new Move(source, destination);
    }

    private static void validatePositionValue(final String[] values) {
        if (values.length != COMMAND_WITH_POSITIONS_SIZE) {
            throw new IllegalArgumentException("잘못된 출발지/도착지입니다.");
        }
    }
}
