package domain.piece;

import domain.Location;
import domain.Section;
import domain.type.Color;
import domain.type.PieceType;
import java.util.List;

public abstract class NonSlidingPiece extends Piece {

    protected NonSlidingPiece(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }

    @Override
    public List<Location> searchPath(final Section start, final Section end) {
        if (isNotMovable(start, end)) {
            throw new IllegalArgumentException(IMPOSSIBLE_MOVE_ERROR_MESSAGE);
        }
        return List.of(end.getLocation());
    }

    abstract protected boolean isNotMovable(final Section start, final Section end);
}
