package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Movement;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Piece {

    protected final Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    public Set<Position> getCatchRoute(final Movement movement) {
        return getRoute(movement);
    }

    public Set<Position> getRoute(final Movement movement) {
        Position position = movement.getLowerPosition();

        final Direction direction = Direction.from(movement);
        if (direction.equals(Direction.HORIZONTAL)) {
            position = movement.getLefterPosition();
        }

        final Set<Position> positions = new HashSet<>();
        for (int i = 1; i < distance(movement); i++) {
            position = position.move(direction.getDx(), direction.getDy());
            positions.add(position);
        }
        return positions;
    }

    private int distance(final Movement movement) {
        return Math.max(movement.getRankDistance(), movement.getFileDistance());
    }
    public boolean isOpponent(final Piece other) {
        return this.color != other.color;
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public abstract boolean canMove(final Movement movement);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    public Color getColor() {
        return color;
    }
}
