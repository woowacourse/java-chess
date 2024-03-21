package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public abstract class SlidingPiece extends Piece {
    public SlidingPiece(Team team) {
        super(team);
    }

    public abstract List<Position> searchPath(Position start, Position destination);
}
