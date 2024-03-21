package chess.domain.piece;

import chess.domain.Position;
import chess.domain.movement.Movement;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

public class Piece {

    private final PieceType pieceType;
    private final Color color;

    public Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public boolean canMove(final Position source, final Position target,
                           final boolean firstMove, final Map<Position, Piece> pieces) {
        return pieceType.getMovements()
                .stream()
                .filter(movement -> movement.isSatisfied(color, firstMove, existEnemy(target, pieces)))
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
        if (obstacles.contains(source) && pieces.get(source).isNotSameColor(color)) {
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
        return pieceType == PieceType.PAWN && isRankMove(source, target)
                && pieces.getOrDefault(target, new Piece(PieceType.EMPTY, Color.NONE)).isNotEmpty();
    }

    private boolean isRankMove(final Position source, final Position target) {
        return source.file() == target.file()
                && (Math.abs(source.rank() - target.rank()) == 1 || Math.abs(source.rank() - target.rank()) == 2);
    }

    private boolean existEnemy(final Position target, final Map<Position, Piece> pieces) {
        return pieces.containsKey(target)
                && pieces.get(target).isNotEmpty()
                && pieces.get(target).isNotSameColor(color);
    }

    private boolean isNotSameColor(final Color color) {
        return this.color != color;
    }

    private boolean isNotEmpty() {
        return pieceType != PieceType.EMPTY;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color);
    }
}
