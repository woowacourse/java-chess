package chess.domain.piece.strategy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.Collections;
import java.util.List;

public class KnightMovementStrategy extends AbstractPieceMovementStrategy {

    public KnightMovementStrategy(final Color color) {
        super(color);
    }

    @Override
    public List<PiecePosition> waypoints(final PiecePosition source,
                                         final PiecePosition destination,
                                         final Piece nullableEnemy) throws IllegalArgumentException {
        validateMove(source, destination, nullableEnemy);
        return Collections.emptyList();
    }

    @Override
    protected void validateMoveWithNoAlly(final PiecePosition source,
                                          final PiecePosition destination,
                                          final Piece nullableEnemy) throws IllegalArgumentException {
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
