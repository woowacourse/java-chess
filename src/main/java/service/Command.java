package service;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String value;

    Command(String value) {
        this.value = value;
    }


    public static Command findRunCommand(String command) {
        return Arrays.stream(Command.values())
                .filter(runCommand -> runCommand.value.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
    }

    public static boolean isStart(String command) {
        return command.equals(START.value);
    }
    public static boolean isEnd(String command) {
        return command.equals(END.value);
    }
    public static boolean isMove(String command) {
        return command.equals(MOVE.value);
    }


}
