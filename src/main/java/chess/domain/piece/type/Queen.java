package chess.domain.piece.type;

import chess.domain.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceNamePattern;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;

public final class Queen extends Piece {
    private static final List<Direction> QUEEN_DIRECTION = List.of(Direction.TOP, Direction.DOWN, Direction.RIGHT, Direction.LEFT,
            Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);

    public Queen(PieceColor color) {
        super(PieceNamePattern.apply(color, "q"), color, PieceType.QUEEN);
    }

    @Override
    public boolean isInMovableRange(Position source, Position target) {
        Direction direction = source.findDirectionTo(target);
        return isMovableDirection(direction) && isMovableDistance(source, target);
    }

    private boolean isMovableDirection(Direction direction) {
        return QUEEN_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(Position source, Position target) {
        int distance = source.calculateDistanceTo(target);
        return distance > 0;
    }
}
