package chess.domain.chessGame;

import chess.domain.piece.Piece;

public class Score {

    private final Piece piece;

    public Score(Piece piece) {
        this.piece = piece;
    }

    public boolean isPawn() {
        return piece.isPawn();
    }

    public double asDouble() {
        return PieceScore.findScore(piece);
    }
}
