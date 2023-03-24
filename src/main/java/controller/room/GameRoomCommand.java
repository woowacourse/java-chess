package controller.room;

import java.util.Arrays;

public enum GameRoomCommand {
    LIST,
    CREATE,
    EXIT,
    JOIN,
    NONE;

    public static GameRoomCommand find(String command) {
        return Arrays.stream(GameRoomCommand.values())
                .filter(gameRoomCommand -> !gameRoomCommand.equals(NONE))
                .filter(gameRoomCommand -> gameRoomCommand.name().equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 입력이 아닙니다."));
    }
}
