package chess.domain.strategy.piecemovestrategy;

import chess.domain.piece.Color;
import chess.domain.position.Position;

public abstract class PawnMoveStrategy {

    private static final PieceType PIECE_TYPE = PieceType.PAWN;

    private final Color color;

    protected PawnMoveStrategy(final Color color) {
        this.color = color;
    }

    abstract boolean isMovableToEmpty(Position from, Position to);

    abstract boolean isMovableToEnemy(Position from, Position to);

    public PieceType getPieceType() {
        return PIECE_TYPE;
    }

    public Color getColor() {
        return color;
    }
}
