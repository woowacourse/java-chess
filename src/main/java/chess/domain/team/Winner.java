package chess.domain.team;

public enum Winner {
    BLACK("블랙"),
    WHITE("화이트"),
    DRAW("무승부");

    private final String value;

    Winner(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
