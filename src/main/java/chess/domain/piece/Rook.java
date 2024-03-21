package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Rook extends SlidingPiece {
    public Rook(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination) {
        return start.isStraightWith(destination);
    }

    @Override
    public List<Position> searchPath(Position start, Position destination) {
        return start.straightPath(destination);
    }
}
