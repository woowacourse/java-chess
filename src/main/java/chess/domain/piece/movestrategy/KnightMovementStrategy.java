package chess.domain.piece.movestrategy;

import chess.domain.piece.MovementType;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.Collections;
import java.util.List;

public class KnightMovementStrategy extends AbstractPieceMovementStrategy {

    private static final double KNIGHT_VALUE = 2.5;

    public KnightMovementStrategy() {
        super(MovementType.KNIGHT);
    }

    @Override
    public List<PiecePosition> waypoints(final PiecePosition source,
                                         final PiecePosition destination,
                                         final Piece nullableEnemy) {
        validateMove(source, destination, nullableEnemy);
        return Collections.emptyList();
    }

    @Override
    public double judgeValue() {
        return KNIGHT_VALUE;
    }

    @Override
    protected void validateMoveWithNoAlly(final PiecePosition source,
                                          final PiecePosition destination,
                                          final Piece nullableEnemy) {
        if (Math.abs(source.fileInterval(destination)) == 1
                && Math.abs(source.rankInterval(destination)) == 2) {
            return;
        }
        if (Math.abs(source.fileInterval(destination)) == 2
                && Math.abs(source.rankInterval(destination)) == 1) {
            return;
        }
        throw new IllegalArgumentException("나이트는 그렇게 이동할 수 없습니다.");
    }
}
