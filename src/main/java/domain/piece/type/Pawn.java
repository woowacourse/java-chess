package domain.piece.type;

import domain.Direction;
import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;
import domain.position.Position;

import java.util.List;

public final class Pawn extends Piece {

    private static final List<Direction> BLACK_DIRECTION = List.of(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
    private static final List<Direction> WHITE_DIRECTION = List.of(Direction.TOP, Direction.TOP_LEFT, Direction.TOP_RIGHT);

    public Pawn(PieceColor color) {
        super(PieceNamePattern.apply(color, "p"), color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return isMovableDirection(source, target) && isMovableDistance(source, target);
    }

    private boolean isMovableDirection(Position source, Position target) {
        Direction direction = source.findDirectionTo(target);
        if (color.isWhite()) {
            return WHITE_DIRECTION.contains(direction);
        }
        return BLACK_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(Position source, Position target) {
        return source.calculateDistanceTo(target) == 1;
    }
}
