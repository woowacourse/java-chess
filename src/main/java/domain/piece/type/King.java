package domain.piece.type;

import domain.Direction;
import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;
import domain.position.Position;

import java.util.List;

public final class King extends Piece {
    private static final List<Direction> KING_DIRECTION = List.of(Direction.TOP, Direction.DOWN, Direction.RIGHT, Direction.LEFT, Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);

    public King(PieceColor color) {
        super(PieceNamePattern.apply(color, "k"), color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        Direction direction = source.findDirectionTo(target);
        return isMovableDirection(direction) && isMovableDistance(source, target);
    }

    private boolean isMovableDirection(Direction direction) {
        return KING_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(Position source, Position target) {
        int distance = source.calculateDistanceTo(target);
        return distance == 1;
    }
}
