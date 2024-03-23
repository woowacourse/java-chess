package view;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    public static final String UNSUPPORTED_COMMAND = "잘못된 명령어 입력입니다.";

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command of(final String command) {
        if (command.equals(START.value)) {
            return START;
        }
        if (command.equals(END.value)) {
            return END;
        }
        if (command.startsWith(MOVE.value)) {
            return MOVE;
        }
        throw new IllegalArgumentException(UNSUPPORTED_COMMAND);
    }
}
