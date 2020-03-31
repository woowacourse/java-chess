package chess.domain;

public enum Team {
    WHITE("white"),
    BLACK("black"),
    NONE("");

    private String name;

    Team(String name) {
        this.name = name;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public Team opposite() {
        if (this == WHITE) {
            return BLACK;
        }

        if (this == BLACK) {
            return WHITE;
        }

        throw new AssertionError("해당하는 팀이 없습니다.");
    }

    public String getName() {
        return name;
    }
}
