package chess.domain.team;

public enum Team {
    WHITE("W"),
    BLACK("B");

    private final String value;

    Team(String value) {
        this.value = value;
    }

    public static Team of(String signature) {
        if (signature.equals("W")) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public Team reverse() {
        if (isBlack()) {
            return WHITE;
        }
        return BLACK;
    }

    public String getValue() {
        return value;
    }
}
