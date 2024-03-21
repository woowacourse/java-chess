package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Bishop extends SlidingPiece {
    public Bishop(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination) {
        return start.isDiagonalWith(destination);
    }

    @Override
    public List<Position> searchPath(Position start, Position destination) {
        return start.diagonalPath(destination);
    }
}
