package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;

public class RookMovement extends AbstractPieceMovement {

    @Override
    protected void validateMoveWithNoAlly(final Path path, final Piece nullableEnemy) throws IllegalArgumentException {
        if (!path.isStraight()) {
            throw new IllegalArgumentException("룩은 직선으로만 이동가능합니다.");
        }
    }
}
