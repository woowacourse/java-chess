package chess.domain.strategy.piecemovestrategy;

import chess.domain.position.Position;
import chess.domain.strategy.movestrategy.DiagonalMove;
import chess.domain.strategy.movestrategy.MoveStrategy;

public final class BishopMove implements PieceMoveStrategy {

    private static final PieceType PIECE_TYPE = PieceType.BISHOP;
    private static final BishopMove INSTANCE;

    static {
        INSTANCE = new BishopMove(DiagonalMove.getInstance());
    }

    private final MoveStrategy moveStrategy;

    private BishopMove(final MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public static BishopMove getInstance() {
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
