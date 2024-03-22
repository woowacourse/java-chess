package chess.domain.piece.pawn;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.PawnMoveStrategy;
import java.util.Map;
import java.util.Set;

public abstract class Pawn extends Piece {
    private final Set<Direction> directions;

    protected Pawn(Color color, Set<Direction> directions) {
        super(color);
        this.directions = directions;
    }

    @Override
    public Set<Position> findPath(Position thisPosition, Position destination) {
        Set<Position> movable = thisPosition.findMovablePositions(directions);

        if (!movable.contains(destination)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return thisPosition.findCourses(thisPosition.findDirectionTo(destination), destination);
    }

    public abstract boolean isCaptureMove(Position thisPosition, Position destination);

    @Override
    public boolean isBlank() {
        return false;
    }

    public abstract Piece update();

    @Override
    public MoveStrategy strategy(Map<Position, Piece> board) {
        return new PawnMoveStrategy(board);
    }
}
