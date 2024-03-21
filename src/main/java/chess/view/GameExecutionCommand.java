package chess.view;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum GameExecutionCommand {
    START("start"),
    END("end"),
    MOVE("move");

    private static final String ARGUMENT_SEPARATOR = " ";

    private final String code;

    GameExecutionCommand(String code) {
        this.code = code;
    }

    //if 무브 명령어면 -> 무브 커맨드 생성
    public static GameExecutionCommand from(String input) {
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
