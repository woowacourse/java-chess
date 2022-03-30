package chess.domain.board;

public enum Score {

    KING(0.0f),
    PAWN(1.0f),
    KNIGHT(2.5f),
    BISHOP(3.0f),
    ROOK(5.0f),
    QUEEN(9.0f);

    private final float value;

    Score(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
