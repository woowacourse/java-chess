package chess.domain.piece.strategy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMovement;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.Collections;
import java.util.List;

public class KnightMovement implements PieceMovement {

    @Override
    public List<PiecePosition> waypoints(final Color currentPieceColor,
                                         final Path path,
                                         final Piece nullableEnemy) throws IllegalArgumentException {
        validateMove(currentPieceColor, path, nullableEnemy);
        return Collections.emptyList();
    }

    @Override
    public void validateMove(final Color currentPieceColor,
                             final Path path,
                             final Piece nullableEnemy) throws IllegalArgumentException {
        validateAllyKill(currentPieceColor, nullableEnemy);
        if (Math.abs(path.fileInterval()) == 1 && Math.abs(path.rankInterval()) == 2) {
            return;
        }
        if (Math.abs(path.fileInterval()) == 2 && Math.abs(path.rankInterval()) == 1) {
            return;
        }
        throw new IllegalArgumentException("나이트는 그렇게 이동할 수 없습니다.");
    }
}
