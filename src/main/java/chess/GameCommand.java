package chess;

import java.util.Arrays;

public enum GameCommand {
    START("start"),
    END("end"),
    MOVE("move");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    private static GameCommand of(String inputValue) {
        return Arrays.stream(GameCommand.values())
                .filter(gameCommand -> gameCommand.command.equals(inputValue))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 명령어를 지원하지 않습니다."));
    }

    public static boolean isStart(String command) {
        return GameCommand.of(command) == GameCommand.START;
    }

    public static boolean isEnd(String command) {
        return GameCommand.of(command) == GameCommand.END;
    }

    public static boolean isMove(String command) {
        return GameCommand.of(command) == GameCommand.MOVE;
    }

    private String getCommand() {
        return command;
    }
}
