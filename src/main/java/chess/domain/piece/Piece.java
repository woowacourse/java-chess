package chess.domain.piece;

import chess.domain.movement.Movement;

public abstract class Piece {
    private final Color color;
    private final PieceType pieceType;

    protected Piece(final Color color, final PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public void validateMovement(int fileInterval, int rankInterval) {
        Movement movement = Movement.of(fileInterval, rankInterval);
        if (!pieceType.getMovements().contains(movement)) {
            throw new IllegalArgumentException("말이 이동할 수 없는 규칙입니다.");
        }
    }

    public Color getColor() {
        return color;
    }

    public String getPieceTypeName() {
        return pieceType.getName();
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
