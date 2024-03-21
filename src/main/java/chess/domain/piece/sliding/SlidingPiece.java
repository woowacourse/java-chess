package chess.domain.piece.sliding;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import java.util.Set;

public abstract class SlidingPiece extends Piece {
    private final Set<Direction> directions;

    public SlidingPiece(Position position, Color color, Set<Direction> directions) {
        super(position, color);
        this.directions = directions;
    }

    public Set<Position> findPathTo(Position destination) {
        Direction direction = position.findDirectionTo(destination);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return position.findCourses(direction, destination);
    }

    public abstract PieceType pieceType();
}
