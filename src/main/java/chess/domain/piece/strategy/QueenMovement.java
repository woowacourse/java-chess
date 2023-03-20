package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceMovement;
import chess.domain.piece.position.Path;

public class QueenMovement implements PieceMovement {

    @Override
    public void validateMove(final Path path, final Piece nullableEnemy) throws IllegalArgumentException {
        if (!path.isStraight() && !path.isDiagonal()) {
            throw new IllegalArgumentException("퀸은 대각선 혹은 직선으로만 이동가능합니다.");
        }
    }
}
