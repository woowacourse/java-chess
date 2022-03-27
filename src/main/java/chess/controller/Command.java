package chess.controller;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;
    private static final Pattern MOVE_PATTERN = Pattern.compile("move [a-h]\\d [a-h]\\d");


    Command(String value) {
        this.value = value;
    }

    public static Command from(String commandText) {
        final String[] splitCommand = commandText.split(" ");
        Command result =  Arrays.stream(values())
                .filter(command -> command.value.equals(splitCommand[0]))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
        if (result == MOVE) {
            checkMove(commandText);
        }
        return result;
    }

    private static void checkMove(String commendText) {
        if (!MOVE_PATTERN.matcher(commendText).matches()) {
            throw new IllegalArgumentException("move 포맷에 맞게 작성해주세요.");
        }
    }
}
