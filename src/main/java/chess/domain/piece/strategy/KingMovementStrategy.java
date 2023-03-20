package chess.domain.piece.strategy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;

public class KingMovementStrategy extends AbstractPieceMovementStrategy {

    public KingMovementStrategy(final Color color) {
        super(color);
    }

    @Override
    protected void validateMoveWithNoAlly(final Path path, final Piece nullableEnemy) throws IllegalArgumentException {
        if (!path.isUnitDistance()) {
            throw new IllegalArgumentException("왕은 한칸만 이동할 수 있습니다.");
        }
    }
}
