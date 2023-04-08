package chess.domain.strategy.piecemovestrategy;

import chess.domain.piece.Color;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public abstract class PawnMoveStrategy {

    private static final Map<Color, PawnMoveStrategy> CACHE = new HashMap<>();
    private static final PieceType PIECE_TYPE = PieceType.PAWN;

    static {
        CACHE.put(Color.WHITE, new WhitePawnMove());
        CACHE.put(Color.BLACK, new BlackPawnMove());
    }

    public static PawnMoveStrategy from(final Color color) {
        return CACHE.get(color);
    }

    abstract public boolean isMovableToEmpty(Position from, Position to);

    abstract public boolean isMovableToEnemy(Position from, Position to);

    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
