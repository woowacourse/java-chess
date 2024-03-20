package view;

import java.util.Arrays;

public enum GameCommand {
    START("start"),
    END("end");

    private String command;

    private GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand from(String inputCommand) {
        return Arrays.stream(values())
                .filter(gameCommand -> gameCommand.command.equals(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력하신 Command가 없습니다."));
    }
}
