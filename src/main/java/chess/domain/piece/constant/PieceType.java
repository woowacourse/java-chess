package chess.domain.piece.constant;

public enum PieceType {

    KING("King", 0),
    QUEEN("Queen", 9),
    BISHOP("Bishop", 3),
    ROOK("Rook", 5),
    KNIGHT("Knight", 2.5),
    PAWN("Pawn", 1),
    ;

    private final String pieceName;
    private final double pieceScore;

    PieceType(final String pieceName, final double pieceScore) {
        this.pieceName = pieceName;
        this.pieceScore = pieceScore;
    }

    public String getPieceName() {
        return pieceName;
    }

    public double getPieceScore() {
        return pieceScore;
    }
}
