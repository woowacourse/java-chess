package chess.domain.piece.movestrategy;

import chess.domain.piece.MovementType;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

public class RookMovementStrategy extends AbstractPieceMovementStrategy {

    public RookMovementStrategy() {
        super(MovementType.ROOK);
    }

    @Override
    protected void validateMoveWithNoAlly(final PiecePosition source,
                                          final PiecePosition destination,
                                          final Piece nullableEnemy) {
        if (!isStraight(source, destination)) {
            throw new IllegalArgumentException("룩은 직선으로만 이동가능합니다.");
        }
    }
}
