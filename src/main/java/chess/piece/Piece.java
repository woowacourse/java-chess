package chess.piece;

import chess.board.Direction;
import chess.board.Position;
import java.util.List;
import java.util.stream.Stream;

public abstract class Piece {

    private final PieceAttributes pieceAttributes;
    private final List<Direction> directions;

    public Piece(PieceType pieceType, Color color, List<Direction> directions) {
        this.pieceAttributes = new PieceAttributes(pieceType, color);
        this.directions = directions;
    }

    public boolean isMovable(Position source, Position destination) {
        Direction direction = Direction.calculateBetween(source, destination);
        return matchesDirection(direction) &&
                isReachable(source, destination, direction);
    }

    protected boolean matchesDirection(Direction direction) {
        return directions.contains(direction);
    }

    protected boolean isReachable(Position source, Position destination, Direction direction) {
        int distance = (int) Stream.iterate(source,
                        position -> position.isNotEquals(destination),
                        direction::nextPosition)
                .count();
        return distance <= getMaxUnitMove();
    }

    protected abstract int getMaxUnitMove();

    public boolean isAttackable(Position source, Position destination) {
        return isMovable(source, destination);
    }

    public boolean isInitPawn() {
        return false;
    }

    public boolean hasSameColorWith(Piece piece) {
        return pieceAttributes.hasSameColorOf(piece.getColor());
    }

    public boolean hasDifferentColorWith(Piece piece) {
        return !hasSameColorWith(piece);
    }

    public boolean hasColorOf(Color color) {
        return pieceAttributes.hasSameColorOf(color);
    }

    public boolean hasAttributesOf(PieceAttributes pieceAttributes) {
        return this.pieceAttributes.equals(pieceAttributes);
    }

    public Color getColor() {
        return pieceAttributes.getColor();
    }
}
