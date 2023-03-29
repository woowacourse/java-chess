package chess.controller.room;

import java.util.Arrays;
import java.util.List;

public enum RoomCommand {
    HISTORY(1),
    CREATE(2),
    JOIN(2),
    END(1),
    EMPTY(0),
    ;

    private final int size;

    RoomCommand(final int size) {
        this.size = size;
    }

    public static final int NAME_INDEX = 1;
    public static final int ROOM_ID_INDEX = 1;
    private static final int COMMAND_INDEX = 0;
    private static final String INVALID_COMMAND_MESSAGE = "올바른 명령어를 입력해주세요.";

    public static RoomCommand from(final List<String> commands) {
        return Arrays.stream(values())
                .filter(command -> command != EMPTY)
                .filter(command -> command.name().equalsIgnoreCase(commands.get(COMMAND_INDEX)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MESSAGE));
    }

    public void validateCommandsSize(final List<String> commands) {
        if (size != commands.size()) {
            throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
        }
    }
}
