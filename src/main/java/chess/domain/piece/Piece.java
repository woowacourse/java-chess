package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public record Piece(PieceType pieceType, Color color) {
    private static final Piece EMPTY_PIECE = new Piece(PieceType.EMPTY, Color.NONE);

    public static Piece getEmptyPiece() {
        return EMPTY_PIECE;
    }

    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> pieces) {
        return pieceType.getMovements()
                .stream()
                .filter(movement -> movement.isSatisfied(color, source, existEnemyAtTarget(target, pieces)))
                .map(Movement::getDirection)
                .anyMatch(direction -> direction.canReach(source, target, findObstacle(source, target, pieces)));
    }

    private List<Position> findObstacle(final Position source, final Position target,
                                        final Map<Position, Piece> pieces) {
        List<Position> obstacles = pieces.entrySet().stream()
                .filter(entry -> entry.getValue().isNotEmpty())
                .map(Entry::getKey)
                .collect(Collectors.toList());

        removeKillableDestinationObstacle(target, pieces, obstacles);
        addObstacleBlockedOnRankMove(source, target, pieces, obstacles);
        return obstacles;
    }

    private void removeKillableDestinationObstacle(final Position source, final Map<Position, Piece> pieces,
                                                   final List<Position> obstacles) {
        if (obstacles.contains(source)
                && color.isNotSameTeam(pieces.getOrDefault(source, Piece.getEmptyPiece()))) {
            obstacles.remove(source);
        }
    }

    private void addObstacleBlockedOnRankMove(final Position source, final Position target,
                                              final Map<Position, Piece> pieces, final List<Position> obstacles) {
        if (isPawnBlockedOnRankMove(source, target, pieces)) {
            obstacles.add(target);
        }
    }

    private boolean isPawnBlockedOnRankMove(final Position source, final Position target,
                                            final Map<Position, Piece> pieces) {
        Piece piece = pieces.getOrDefault(target, Piece.getEmptyPiece());
        return pieceType == PieceType.PAWN && isRankMove(source, target)
                && piece.isNotEmpty();
    }

    private boolean isRankMove(final Position source, final Position target) {
        return source.file() == target.file();
    }

    private boolean existEnemyAtTarget(final Position target, final Map<Position, Piece> pieces) {
        return pieces.containsKey(target)
                && color.isNotSameTeam(pieces.get(target));
    }

    private boolean isNotEmpty() {
        return pieceType != PieceType.EMPTY;
    }
}
