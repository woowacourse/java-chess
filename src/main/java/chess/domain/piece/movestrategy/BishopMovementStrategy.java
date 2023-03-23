package chess.domain.piece.movestrategy;

import chess.domain.piece.MovementType;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

public class BishopMovementStrategy extends AbstractPieceMovementStrategy {

    private static final double BISHOP_VALUE = 3;

    public BishopMovementStrategy() {
        super(MovementType.BISHOP);
    }

    @Override
    protected void validateMoveWithNoAlly(final PiecePosition source,
                                          final PiecePosition destination,
                                          final Piece nullableEnemy) {
        if (!isDiagonal(source, destination)) {
            throw new IllegalArgumentException("비숍은 대각선으로만 이동할 수 있습니다.");
        }
    }

    @Override
    public double judgeValue() {
        return BISHOP_VALUE;
    }
}
