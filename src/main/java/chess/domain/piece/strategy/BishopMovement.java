package chess.domain.piece.strategy;

import chess.domain.piece.AbstractPieceMovement;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;

public class BishopMovement extends AbstractPieceMovement {

    @Override
    protected void validateMoveWithNoAlly(final Path path, final Piece nullableEnemy) throws IllegalArgumentException {
        if (!path.isDiagonal()) {
            throw new IllegalArgumentException("비숍은 대각선으로만 이동할 수 있습니다.");
        }
    }
}
