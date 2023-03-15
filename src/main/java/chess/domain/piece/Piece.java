package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.Square;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected final Camp camp;

    public Piece(final Camp camp) {
        this.camp = camp;
    }

    public List<Square> pathToTarget(final Square source, final Square target) {
        List<Square> path = new ArrayList<>();
        if (isMovable(source, target)) {
            path = tracePath(source, target, path);
        }
        return path;
    }

    abstract protected boolean isMovable(final Square source, final Square target);

    abstract protected List<Square> tracePath(final Square source, final Square target, List<Square> path);
}
