package chess.domain;

import chess.domain.piece.Piece;

public enum PieceKind {
    QUEEN("queen", 9),
    ROOK("rook", 5),
    BISHOP("bishop", 3),
    KNIGHT("knight", 2.5),
    PAWN("pawn", 1),
    KING("king", 0);

    private final String pieceName;
    private final double score;

    PieceKind(String pieceName, double score) {
        this.pieceName = pieceName;
        this.score = score;
    }

    public boolean isSamePieceWith(final Piece piece) {
        return piece.isSamePiece(pieceName);
    }

    public double calculateScore(final int count) {
        return score * count;
    }
}
