package chess.domain.piece.sliding;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.strategy.GeneralMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import java.util.Map;
import java.util.Set;

public abstract class SlidingPiece extends Piece {
    private final Set<Direction> directions;

    public SlidingPiece(Color color, Set<Direction> directions) {
        super(color);
        this.directions = directions;
    }

    @Override
    public Set<Position> findPath(Position thisPosition, Position destination) {
        Direction direction = thisPosition.findDirectionTo(destination);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return thisPosition.findCourses(direction, destination);
    }
    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public MoveStrategy strategy(Map<Position, Piece> board) {
        return new GeneralMoveStrategy(board);
    }
}
