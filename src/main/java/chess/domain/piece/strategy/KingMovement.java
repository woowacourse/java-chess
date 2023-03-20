package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceMovement;
import chess.domain.piece.position.Path;

public class KingMovement implements PieceMovement {

    @Override
    public void validateMove(final Path path, final Piece nullableEnemy) throws IllegalArgumentException {
        if (!path.isUnitDistance()) {
            throw new IllegalArgumentException("왕은 한칸만 이동할 수 있습니다.");
        }
    }
}
