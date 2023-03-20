package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceMovement;
import chess.domain.piece.position.Path;

public class RookMovement implements PieceMovement {

    @Override
    public void validateMove(final Path path, final Piece nullableEnemy) throws IllegalArgumentException {
        if (!path.isStraight()) {
            throw new IllegalArgumentException("룩은 직선으로만 이동가능합니다.");
        }
    }
}
