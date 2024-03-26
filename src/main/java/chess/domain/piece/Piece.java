package chess.domain.piece;

import chess.domain.square.Square;

public class Piece {

    private final PieceType type;
    private final PieceColor color;

    public Piece(final PieceType type, final PieceColor color) {
        this.type = type;
        this.color = color;
    }

    public boolean isSameColor(final PieceColor other) {
        return color == other;
    }

    public boolean canMove(final Square source, final Square target) {
        if (type == PieceType.PAWN) {
            return type.canMove(source, target) && source.isNotBackward(target, color);
        }
        return type.canMove(source, target);
    }

    public PieceType getType() {
        return type;
    }

    public PieceColor getColor() {
        return color;
    }
}
