package chess.domain.piece.obstaclerule;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public abstract class ObstacleRule {
    public abstract List<Position> findObstacle(final Position source, final Position target,
                                                final Map<Position, Piece> pieces);

    protected void removeCapturableTargetFromObstacle(final Position source, final Position target,
                                                      final Map<Position, Piece> pieces,
                                                      final List<Position> obstacles) {
        Piece sourcePiece = pieces.getOrDefault(source, Piece.getEmptyPiece());
        if (obstacles.contains(target)
                && sourcePiece.isNotSameTeam(pieces.getOrDefault(target, Piece.getEmptyPiece()))) {
            obstacles.remove(target);
        }
    }

    protected List<Position> getNotEmptyPiecePositions(final Map<Position, Piece> pieces) {
        return pieces.entrySet().stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }

    protected void removeSourcePosition(final Position source, final List<Position> positions) {
        positions.remove(source);
    }
}
