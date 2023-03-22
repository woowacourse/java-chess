package chess.view;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    FORCE_QUIT("exit");

    private static final String WRONG_COMMAND_ERROR_MESSAGE = "잘못된 커맨드 입니다.";

    private final String answer;

    Command(final String answer) {
        this.answer = answer;
    }

    public static Command findSingleCommand(String input) {
        return Arrays.stream(values())
                .filter(command -> command != MOVE)
                .filter(command -> command.answer.equalsIgnoreCase(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND_ERROR_MESSAGE));
    }

    public String getAnswer() {
        return answer;
    }

}
