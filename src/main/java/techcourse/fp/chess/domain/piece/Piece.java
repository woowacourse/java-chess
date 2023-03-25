package techcourse.fp.chess.domain.piece;

import java.util.List;
import techcourse.fp.chess.domain.Position;

public abstract class Piece {

    protected final Color color;

    public Piece(final Color color) {
        this.color = color;
    }


    public abstract List<Position> findPath(final Position source, final Position target,
                                            final Piece targetPiece);

    public boolean isEmpty() {
        return color.isEmpty();
    }

    public Color getColor() {
        return color;
    }
}
