package chess.domain.piece;

import chess.domain.piece.strategy.MovementStrategy;
import chess.domain.position.Position;

public class Piece {
    protected final PieceType type;

    public Piece(PieceType type) {
        this.type = type;
    }

    public boolean isInMovableRange(final Position source, final Position target) {
        MovementStrategy movementStrategy = type.movementStrategy();
        return movementStrategy.isMovable(source, target);
    }

    public boolean isColor(final PieceColor color) {
        return this.type.color() == color;
    }

    public boolean isType(PieceType pieceType) {
        return this.type == pieceType;
    }

    public String name() {
        return type.pieceName();
    }

    public PieceColor color() {
        return type.color();
    }
}
