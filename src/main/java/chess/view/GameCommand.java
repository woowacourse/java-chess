package chess.view;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum GameCommand {
    START("start"),
    END("end"),
    MOVE("move");

    private static final String ARGUMENT_SEPARATOR = " ";

    private final String code;

    GameCommand(String code) {
        this.code = code;
    }

    public static GameCommand from(String input) {
        return Arrays.stream(values())
                .filter(command -> command.commandStartsWithCode(input))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("해당 커맨드를 찾을 수 없습니다"));
    }

    private boolean commandStartsWithCode(String input) {
        String inputCommand = input.split(ARGUMENT_SEPARATOR)[0];
        return inputCommand.equals(code);
    }

    public String getCode() {
        return code;
    }
}
