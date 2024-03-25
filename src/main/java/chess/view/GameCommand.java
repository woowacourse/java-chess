package chess.view;

import java.util.Arrays;

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
                .orElseThrow(() -> new IllegalArgumentException(input + "은 존재하지 않는 커멘드 입니다."));
    }

    private boolean commandStartsWithCode(String input) {
        String inputCommand = input.split(ARGUMENT_SEPARATOR)[0];
        return inputCommand.equals(code);
    }

    public String getCode() {
        return code;
    }
}
