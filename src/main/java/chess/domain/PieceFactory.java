package chess.domain;

public enum PieceFactory {
    WHITE(1),
    BLACK(8);

    private final int edgeRow;

    PieceFactory(int edgeRow) {
        this.edgeRow = edgeRow;
    }

    static Piece of(int edgeRow, PieceType pieceType) {
        if (edgeRow == WHITE.edgeRow) {
            return new WhitePiece(pieceType);
        }
        return new BlackPiece(pieceType);
    }
}

