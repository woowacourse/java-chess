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

    public String getValue() {
        return value;
    }
}
