package chess.domain.piece;

public enum Team {

    WHITE(1),
    BLACK(0);

    private final int value;

    Team(int value) {
        this.value = value;
    }

    public static Team from(int value) {
        if (value == 1) {
            return WHITE;
        }
        return BLACK;
    }

    public Team getEnemy() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public int getValue() {
        return value;
    }
}
