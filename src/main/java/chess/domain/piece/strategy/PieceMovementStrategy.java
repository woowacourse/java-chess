package chess.domain.piece.strategy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public interface PieceMovementStrategy {

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    List<PiecePosition> waypoints(final PiecePosition source,
                                  final PiecePosition destination,
                                  final Piece nullableEnemy) throws IllegalArgumentException;

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    void validateMove(final PiecePosition source,
                      final PiecePosition destination,
                      final Piece nullableEnemy) throws IllegalArgumentException;

    Color color();

    double judgeValue();

    default boolean isKing() {
        return false;
    }

    default boolean isPawn() {
        return false;
    }
}
