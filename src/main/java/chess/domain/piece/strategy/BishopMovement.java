package chess.domain.piece.strategy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMovement;
import chess.domain.piece.position.Path;

public class BishopMovement implements PieceMovement {

    @Override
    public void validateMove(final Color currentPieceColor, final Path path, final Piece nullableEnemy) throws IllegalArgumentException {
        validateAllyKill(currentPieceColor, nullableEnemy);
        if (!path.isDiagonal()) {
            throw new IllegalArgumentException("비숍은 대각선으로만 이동할 수 있습니다.");
        }
    }
}
