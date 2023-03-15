package chess.domain.piece;

import chess.domain.movement.Movement;

public abstract class Piece {
    private final Color color;
    private final PieceType pieceType;

    protected Piece(final Color color, final PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public boolean canMove(int fileInterval, int rankInterval) {
        Movement movement = Movement.from(fileInterval, rankInterval);
        return pieceType.getMovements().contains(movement);
    }

    public Color getColor() {
        return color;
    }

    public String getPieceTypeName() {
        return pieceType.getName();
    }
}
