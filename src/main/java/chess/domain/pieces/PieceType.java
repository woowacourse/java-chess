package chess.domain.pieces;

public enum PieceType {

    PAWN(1),
    ROOK(5),
    Knight(2.5),
    Bishop(3),
    QUEEN(9),
    KING(0),
    EMPTY(0);

    final double score;

    PieceType(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
