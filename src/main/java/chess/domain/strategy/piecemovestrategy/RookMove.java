package chess.domain.strategy.piecemovestrategy;

import chess.domain.position.Position;
import chess.domain.strategy.movestrategy.MoveStrategy;
import chess.domain.strategy.movestrategy.StraightMove;

public final class RookMove implements PieceMoveStrategy {

    private static final PieceType PIECE_TYPE = PieceType.ROOK;
    private static final RookMove INSTANCE;

    static {
        INSTANCE = new RookMove(StraightMove.getInstance());
    }

    private final MoveStrategy moveStrategy;

    private RookMove(final MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public static RookMove getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return moveStrategy.isMovable(from, to);
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
