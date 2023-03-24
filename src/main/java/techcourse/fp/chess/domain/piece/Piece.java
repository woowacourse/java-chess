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

    public boolean isSameColor(Color other) {
        return color == other;
    }

    public boolean isEmpty() {
        return color.isEmpty();
    }

    public boolean isSamePieceType(PieceType other) {
        return pieceType == other;
    }

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return pieceType.getScore();
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
