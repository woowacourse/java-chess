package view;

import java.util.Arrays;

public enum GameCommand {

    START("start"),
    MOVE("move"),
    END("end");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand of(String inputCommand) {
        return Arrays.stream(values())
                .filter(gameCommand -> gameCommand.getCommand().equals(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 사용할 수 없는 명령어를 입력했습니다."));
    }

    private String getCommand() {
        return command;
    }
}
