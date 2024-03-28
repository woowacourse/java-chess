package chess.domain.piece.obstaclerule;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class StraightCaptureObstacleRule extends ObstacleRule {

    @Override
    public List<Position> findObstacle(final Position source, final Position target,
                                       final Map<Position, Piece> pieces) {
        List<Position> obstacles = getNotEmptyPiecePositions(pieces);
        removeSourcePosition(source, obstacles);
        removeCapturableTargetFromObstacle(source, target, pieces, obstacles);
        return obstacles;
    }
}
