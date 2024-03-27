package chessgame.view;

import java.util.Arrays;

public enum ChessCommand {
    PENDING(""),
    START("start"),
    END("end"),
    MOVE("move");

    private final String commandText;

    ChessCommand(final String commandText) {
        this.commandText = commandText;
    }

    public static ChessCommand from(final String commandText){
        return Arrays.stream(values())
                .filter(value -> value.commandText.equals(commandText))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s는 없는 명령입니다.", commandText)));
    }
}
