package controller.room;

import java.util.Arrays;

public enum GameRoomCommand {
    LIST,
    CREATE,
    EXIT,
    JOIN,
    NONE;

    public static final int STANDARD_ROOM_COMMAND_LENGTH = 1;
    public static final int SPECIAL_ROOM_COMMAND_LENGTH = 2;
    public static final String ROOM_COMMAND_ERROR_MESSAGE = "입력이 올바르지 않습니다.";

    public static GameRoomCommand find(String command) {
        return Arrays.stream(GameRoomCommand.values())
                .filter(gameRoomCommand -> !gameRoomCommand.equals(NONE))
                .filter(gameRoomCommand -> gameRoomCommand.name().equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 입력이 아닙니다."));
    }

    public static void validateCommandLength(int given, int expected) {
        if (given != expected) {
            throw new IllegalArgumentException(ROOM_COMMAND_ERROR_MESSAGE);
        }
    }
}
