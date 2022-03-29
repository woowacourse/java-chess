package chess.model;

public enum Team {

    BLACK("검은색"),
    WHITE("흰색"),
    NONE("-"),
    ;

    private final String value;

    Team(String value) {
        this.value = value;
    }

    public Team opponent() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String getValue() {
        return value;
    }
}
