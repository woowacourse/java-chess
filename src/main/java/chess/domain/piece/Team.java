package chess.domain.piece;

public enum Team {

    BLACK("black"),
    WHITE("white"),
    NONE("none");

    private final String value;

    Team(String value) {
        this.value = value;
    }

    public Team oppositeTeam() {
        if (this == NONE) {
            throw new IllegalStateException("[ERROR] 상대팀이 없습니다.");
        }
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String getValue() {
        return value;
    }
}
