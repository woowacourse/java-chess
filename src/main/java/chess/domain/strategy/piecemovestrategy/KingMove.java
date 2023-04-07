package chess.domain.strategy.piecemovestrategy;

import chess.domain.position.Position;
import chess.domain.strategy.movestrategy.*;

import java.util.List;

public final class KingMove implements PieceMoveStrategy {

    private static final PieceType PIECE_TYPE = PieceType.KING;
    private static final KingMove INSTANCE;

    static {
        final List<MoveStrategy> moveStrategies = List.of(DiagonalMove.getInstance(), StraightMove.getInstance());
        final MoveStrategy queenMoveStrategy = new UnionMoveStrategy(moveStrategies);
        final List<MoveStrategy> queenMoveAndNonSliding = List.of(queenMoveStrategy, NonSlidingMove.getInstance());
        MoveStrategy kingMoveStrategy = new IntersectionMoveStrategy(queenMoveAndNonSliding);

        INSTANCE = new KingMove(kingMoveStrategy);
    }

    private final MoveStrategy moveStrategy;

    private KingMove(final MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public static KingMove getInstance() {
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
