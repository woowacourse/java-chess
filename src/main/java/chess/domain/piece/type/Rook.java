package chess.domain.piece.type;

import chess.domain.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceNamePattern;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;

public final class Rook extends Piece {
    private static final List<Direction> ROOK_DIRECTION = List.of(Direction.TOP, Direction.DOWN, Direction.RIGHT, Direction.LEFT);

    public Rook(PieceColor color) {
        super(PieceNamePattern.apply(color, "r"), color, PieceType.ROOK);
    }

    @Override
    public boolean isInMovableRange(Position source, Position target) {
        Direction direction = Direction.of(source, target);
        return isMovableDirection(direction) && isMovableDistance(source, target);
    }

    private boolean isMovableDirection(Direction direction) {
        return ROOK_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(Position source, Position target) {
        int distance = source.calculateDistanceTo(target);
        return distance > 0;
    }
}
