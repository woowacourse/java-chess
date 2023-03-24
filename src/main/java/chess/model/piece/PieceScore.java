package chess.model.piece;

public class PieceScore {

    public static final PieceScore ZERO = new PieceScore(0.0d);
    public static final PieceScore QUEEN = new PieceScore(9.0d);
    public static final PieceScore ROOK = new PieceScore(5.0d);
    public static final PieceScore BISHOP = new PieceScore(3.0d);
    public static final PieceScore KNIGHT = new PieceScore(2.5d);
    public static final PieceScore PAWN = new PieceScore(1.0d);
    public static final PieceScore PAWN_OFFSET = new PieceScore(0.5d);

    private final double value;

    private PieceScore(final double value) {
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
