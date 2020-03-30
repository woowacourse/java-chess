package chess.domain.piece;

public enum Team {
    BLACK("흑"),
    WHITE("백"),
    NONE("X");

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public Team next() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        if (this.equals(WHITE)) {
            return BLACK;
        }
        throw new IllegalArgumentException("잘못된 팀 입력입니다.");
    }

    public boolean isEnemy(Team team) {
        if (this.equals(BLACK)) {
            return WHITE.equals(team);
        }
        if (this.equals(WHITE)) {
            return BLACK.equals(team);
        }
        throw new IllegalArgumentException("잘못된 팀 입력입니다.");
    }

    public boolean isNotEnemy(Team team) {
        if (this.equals(BLACK)) {
            return !WHITE.equals(team);
        }
        if (this.equals(WHITE)) {
            return !BLACK.equals(team);
        }
        throw new IllegalArgumentException("잘못된 팀 입력입니다.");
    }

    public static String getPieceName(Piece piece) {
        if (piece.isSameTeam(WHITE)) {
            return piece.getName().toLowerCase();
        }
        return piece.getName();
    }

    public String getName() {
        return name;
    }
}
