package view;

import java.util.Arrays;

public enum GameCommand {
    START("start"), END("end"), MOVE("move");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand from(String command) {
        return Arrays.stream(values())
                     .filter(gameCommand -> gameCommand.command.equals(command))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("입력하신 Command가 없습니다."));
    }
}
