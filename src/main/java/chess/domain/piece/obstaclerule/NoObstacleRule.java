package chess.domain.piece.obstaclerule;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoObstacleRule extends ObstacleRule {

    @Override
    public List<Position> findObstacle(final Position source, final Position target,
                                       final Map<Position, Piece> pieces) {
        return new ArrayList<>();
    }
}
