package chess.domain.strategy.piecemovestrategy;

import chess.domain.position.Position;
import chess.domain.strategy.movestrategy.DiagonalMove;
import chess.domain.strategy.movestrategy.MoveStrategy;
import chess.domain.strategy.movestrategy.StraightMove;
import chess.domain.strategy.movestrategy.UnionMoveStrategy;

import java.util.List;

public final class QueenMove implements PieceMoveStrategy {

    private static final PieceType PIECE_TYPE = PieceType.QUEEN;
    private static final QueenMove INSTANCE;

    static {
        final List<MoveStrategy> moveStrategies = List.of(DiagonalMove.getInstance(), StraightMove.getInstance());
        MoveStrategy queenMoveStrategy = new UnionMoveStrategy(moveStrategies);

        INSTANCE = new QueenMove(queenMoveStrategy);
    }

    private final MoveStrategy moveStrategy;

    private QueenMove(final MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public static QueenMove getInstance() {
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
