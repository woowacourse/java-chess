package chess.piece;

import chess.Position;
import java.util.List;

public abstract class Piece {

    Piece() {
    }

    public abstract boolean canMove(Position start, Position target);

    public abstract List<Position> findAllRouteToTarget(Position start, Position target);
}
