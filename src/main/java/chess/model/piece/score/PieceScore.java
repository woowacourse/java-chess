package chess.model.piece.score;

public final class PieceScore {

    private final double value;

    PieceScore(final double value) {
        this.value = value;
    }

    public PieceScore plus(final PieceScore pieceScore) {
        return new PieceScore(this.value + pieceScore.value);
    }

    public PieceScore minus(final PieceScore pieceScore) {
        return new PieceScore(this.value - pieceScore.value);
    }

    public double getValue() {
        return value;
    }
}
