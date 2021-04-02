package chess.domain.order;

import chess.domain.piece.Piece;

public class MoveResult {
    private final Piece capturedPiece;

    public MoveResult(Piece capturedPiece) {
        this.capturedPiece = capturedPiece;
    }

    public boolean isCaptured() {
        return capturedPiece.isNotBlank();
    }

    public Piece getCapturedPiece() {
        if (isCaptured()) {
            return capturedPiece;
        }
        throw new IllegalArgumentException("잡힌 말이 없습니다.");
    }
}
