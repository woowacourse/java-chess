package chess;

import java.util.Arrays;

public enum GameCommand {
    START("start"), END("end");

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

    public static boolean isStart(String s) {
        return GameCommand.of(s) == GameCommand.START;
    }

    public static boolean isEnd(String s) {
        return GameCommand.of(s) == GameCommand.END;
    }

    private String getCommand() {
        return command;
    }
}
