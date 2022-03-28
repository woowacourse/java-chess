package chess.domain.board;

public enum Score {

    King(0.0f),
    Pawn(1.0f),
    Knight(2.5f),
    Bishop(3.0f),
    Rook(5.0f),
    Queen(9.0f);

    private final float value;

    Score(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
