package techcourse.fp.chess.domain.piece;

import java.util.List;
import techcourse.fp.chess.domain.Position;

public abstract class Piece {

    protected final Color color;
    protected final PieceType pieceType;

    public Piece(final Color color, final PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
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
