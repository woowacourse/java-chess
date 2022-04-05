package chess.domain.classification;

public enum CommandCase {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status"),
    ELSE("else");

    private final String value;

    CommandCase(final String value) {
        this.value = value;
    }

    public static boolean checkStart(final String input) {
        return input.equals(START.getValue());
    }

    public static boolean checkEnd(final String input) {
        return input.equals(END.getValue());
    }

    public static boolean checkStatus(final String input) {
        return input.equals(STATUS.getValue());
    }

    public static boolean checkMove(final String input) {
        return input.contains(MOVE.getValue());
    }

    public String getValue() {
        return value;
    }
}
