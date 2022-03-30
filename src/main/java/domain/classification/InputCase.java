package domain.classification;

public enum InputCase {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status"),
    ELSE("else");

    private final String value;

    InputCase(final String value) {
        this.value = value;
    }

    public static void validateUserCommand(final String input) {
        validateNullCheck(input);
        validateNotAllowStartCommand(input);
    }

    public static InputCase responseUserCommand(final String input) {
        validateNullCheck(input);
        validateNotAllowCommand(input);
        if (input.equals(STATUS.getValue())) {
            return STATUS;
        }
        if (input.equals(MOVE.getValue())) {
            return MOVE;
        }
        if (input.equals(END.getValue())) {
            return END;
        }
        return ELSE;
    }

    private static void validateNullCheck(final String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 게임 명령에 공백을 입력할 수 없습니다.");
        }
    }

    private static void validateNotAllowStartCommand(final String input) {
        if (!(input.equals(START.getValue()))) {
            throw new IllegalArgumentException("[ERROR] start 이외의 문자는 입력할 수 없습니다.");
        }
    }

    private static void validateNotAllowCommand(final String input) {
        if (!(input.equals(END.getValue()) || input.equals(MOVE.getValue()) ||
                input.equals(STATUS.getValue()))) {
            String message = String.format("[ERROR] %s, %s, %s 이외의 문자는 입력할 수 없습니다.",
                    END.getValue(), MOVE.getValue(), STATUS.getValue());
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean checkStart(final String input) {
        return input.equals(START.getValue());
    }

    public String getValue() {
        return value;
    }
}
