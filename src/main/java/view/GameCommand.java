package view;

import java.util.Arrays;

public enum GameCommand {
    START("start"),
    MOVE("move"),
    END("end");

    private final String gameCommand;

    GameCommand(String gameCommand) {
        this.gameCommand = gameCommand;
    }

    public static GameCommand from(String gameCommand) {
        return Arrays.stream(GameCommand.values())
                .filter(command -> command.isExistCommand(gameCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임 커맨드입니다."));
    }

    public boolean isExistCommand(String command) {
        return this.gameCommand.equals(command);
    }
}
