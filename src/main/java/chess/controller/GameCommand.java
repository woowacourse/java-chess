package chess.controller;

import chess.domain.Position;

import java.util.Arrays;

public enum GameCommand {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String name;

    GameCommand(String name) {
        this.name = name;
    }

    public static GameCommand of(String[] GameCommand) {
        return Arrays.stream(values())
                .filter(command -> command.name.equals(GameCommand[0]))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 명령어입니다."));

    }

    public static Position getSourcePosition(String[] gameCommand) {
        return Position.findPosition(gameCommand[1]);
    }

    public static Position getTargetPosition(String[] gameCommand) {
        return Position.findPosition(gameCommand[2]);
    }
}
