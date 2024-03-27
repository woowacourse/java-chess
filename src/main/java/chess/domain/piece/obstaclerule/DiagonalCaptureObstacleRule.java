package chess.domain.piece.obstaclerule;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class DiagonalCaptureObstacleRule extends ObstacleRule {

    @Override
    public List<Position> findObstacle(final Position source, final Position target,
                                       final Map<Position, Piece> pieces) {
        List<Position> obstacles = getNotEmptyPiecePositions(pieces);
        removeCapturableTargetFromObstacle(source, target, pieces, obstacles);
        removeSourcePosition(source, obstacles);
        addTargetToObstacle(source, target, pieces, obstacles);
        return obstacles;
    }

    private void addTargetToObstacle(final Position source, final Position target,
                                     final Map<Position, Piece> pieces, final List<Position> obstacles) {
        Piece sourcePiece = pieces.get(source);
        Piece targetPiece = pieces.getOrDefault(target, Piece.getEmptyPiece());

        if (canNotKillTargetByStraightMove(source, target, sourcePiece, targetPiece)
                || canNotMoveToTargetByDiagonalMove(source, target, sourcePiece, targetPiece)) {
            obstacles.add(target);
        }
    }

    // 도착 위치가 상대편 말이지만 출발 위치와 도착 위치가 직선 이동일 경우
    private boolean canNotKillTargetByStraightMove(final Position source, final Position target,
                                                   final Piece sourcePiece, final Piece targetPiece) {
        return targetPiece.isNotSameTeam(sourcePiece) &&
                !targetPiece.isEmpty() &&
                sourcePiece.isRankMove(source, target);
    }

    // 도착 위치가 비어있고 출발 위치와 도착 위치가 대각 이동일 경우
    private boolean canNotMoveToTargetByDiagonalMove(final Position source, final Position target,
                                                     final Piece sourcePiece, final Piece targetPiece) {
        return targetPiece.isEmpty()
                && source.isDiagonalBy(target);
    }
}
