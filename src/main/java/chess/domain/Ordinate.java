package chess.domain;

public enum Ordinate {

    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1");

    private final String value;

    Ordinate(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
