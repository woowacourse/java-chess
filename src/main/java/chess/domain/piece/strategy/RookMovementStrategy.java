package chess.domain.piece.strategy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

public class RookMovementStrategy extends AbstractPieceMovementStrategy {

    public RookMovementStrategy(final Color color) {
        super(color);
    }

    @Override
    protected void validateMoveWithNoAlly(final PiecePosition source,
                                          final PiecePosition destination,
                                          final Piece nullableEnemy) throws IllegalArgumentException {
        if (!isStraight(source, destination)) {
            throw new IllegalArgumentException("룩은 직선으로만 이동가능합니다.");
        }
    }
}
