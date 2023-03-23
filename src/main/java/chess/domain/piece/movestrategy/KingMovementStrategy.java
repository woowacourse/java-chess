package chess.domain.piece.movestrategy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

public class KingMovementStrategy extends AbstractPieceMovementStrategy {

    private static final double KING_VALUE = 0;

    public KingMovementStrategy(final Color color) {
        super(color);
    }

    @Override
    protected void validateMoveWithNoAlly(final PiecePosition source,
                                          final PiecePosition destination,
                                          final Piece nullableEnemy) {
        if (!isUnitDistance(source, destination)) {
            throw new IllegalArgumentException("왕은 한칸만 이동할 수 있습니다.");
        }
    }

    @Override
    public double judgeValue() {
        return KING_VALUE;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
