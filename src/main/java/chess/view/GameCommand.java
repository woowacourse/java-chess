package chess.view;

import java.util.Arrays;

public enum GameCommand {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand from(String input) {
        return Arrays.stream(GameCommand.values())
                .filter(gameCommand -> gameCommand.command.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 커멘드입니다."));
    }
}
