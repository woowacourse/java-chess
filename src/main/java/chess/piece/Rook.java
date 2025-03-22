package chess.piece;

import chess.Movement;
import chess.Position;
import chess.Row;
import java.util.List;

public class Rook extends Piece {

    @Override
    public boolean canMove(Position start, Position target) {
        return start.onStraight(target);
    }

    @Override
    public List<Position> findAllRouteToTarget(Position start, Position target) {

    }

}
