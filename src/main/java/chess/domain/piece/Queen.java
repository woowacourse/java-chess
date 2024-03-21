package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Queen extends SlidingPiece {
    public Queen(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination) {
        return start.isStraightWith(destination) || start.isDiagonalWith(destination);
    }

    @Override
    public List<Position> searchPath(Position start, Position destination) {
        if (start.isDiagonalWith(destination)) {
            return start.diagonalPath(destination);
        }
        return start.straightPath(destination);
    }
}
