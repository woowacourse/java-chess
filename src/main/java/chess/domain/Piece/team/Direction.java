package chess.domain.Piece.team;

public enum  Direction {
    UP(1),
    DOWN(-1);

    private int value;

    Direction(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
