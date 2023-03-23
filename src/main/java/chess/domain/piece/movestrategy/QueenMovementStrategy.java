package chess.domain.piece.movestrategy;

import chess.domain.piece.MovementType;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

public class QueenMovementStrategy extends AbstractPieceMovementStrategy {

    public QueenMovementStrategy() {
        super(MovementType.QUEEN);
    }

    @Override
    protected void validateMoveWithNoAlly(final PiecePosition source,
                                          final PiecePosition destination,
                                          final Piece nullableEnemy) {
        if (!isStraight(source, destination) && !isDiagonal(source, destination)) {
            throw new IllegalArgumentException("퀸은 대각선 혹은 직선으로만 이동가능합니다.");
        }
    }
}
