package domain.piece.type;

import domain.Direction;
import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;
import domain.position.Position;

import java.util.List;

public final class Rook extends Piece {

    private static final List<Direction> ROOK_DIRECTION = List.of(Direction.TOP, Direction.DOWN, Direction.RIGHT, Direction.LEFT);

    public Rook(PieceColor color) {
        super(PieceNamePattern.apply(color, "r"), color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        Direction direction = source.findDirectionTo(target);
        return isMovableDirection(direction) && isMovableDistance(source, target, direction);
    }

    private boolean isMovableDirection(Direction direction) {
        return ROOK_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(Position source, Position target, Direction direction) {
        int distance = source.calculateDistanceTo(target);
        return distance > 0;
    }
}
