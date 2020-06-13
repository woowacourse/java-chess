package chess.domain.piece.team;

public enum Team {
    WHITE(Team::isBlack, "WHITE"),
    BLACK(Team::isWhite, "BLACK"),
    NOT_ASSIGNED((ignored) -> {
        throw new IllegalStateException("팀이 정해져있지 않아 적을 식별할 수 없습니다.");
    }, "Not Assigned");

    private final OppositeIdentifier oppositeIdentifier;
    private final String name;

    Team(OppositeIdentifier oppositeIdentifier, String name) {
        this.oppositeIdentifier = oppositeIdentifier;
        this.name = name;
    }

    public boolean isSame(Team team) {
        return this == team;
    }

    public boolean isOpposite(Team other) {
        return oppositeIdentifier.isOpposite(other);
    }

    public static String convertName(String name, Team team) {
        if (team.isWhite()) {
            return name.toLowerCase();
        }

        if (team.isBlack()) {
            return name.toUpperCase();
        }

        return name;
    }

    public Team getOpposite() {
        if (this == WHITE) {
            return BLACK;
        }

        if (this == BLACK) {
            return WHITE;
        }

        return NOT_ASSIGNED;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    private interface OppositeIdentifier {
        boolean isOpposite(Team team);
    }

    @Override
    public String toString() {
        return name;
    }
}
