package chess.domain.piece;

import chess.domain.Position;
import chess.domain.movement.Movement;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Piece {

    private final PieceType pieceType;
    private final Color color;

    protected Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> pieces) {
        return getPieceType().getMovements()
                .stream()
                .filter(movement -> filterMovement(movement, source, target, pieces))
                .map(Movement::getDirection)
                .anyMatch(direction -> direction.canReach(source, target, findObstacle(source, target, pieces)));
    }

    abstract protected boolean filterMovement(Movement movement, Position source, Position target,
                                              Map<Position, Piece> pieces);

    private List<Position> findObstacle(final Position source, final Position target,
                                        final Map<Position, Piece> pieces) {
        return pieces.entrySet().stream()
                .filter(entry -> filterObstacles(source, target, entry))
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }

    abstract protected boolean filterObstacles(Position source, Position target, Entry<Position, Piece> entry);

    public boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public boolean isNotEmpty() {
        return pieceType != PieceType.EMPTY;
    }

    public boolean isEmpty() {
        return pieceType == PieceType.EMPTY;
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
