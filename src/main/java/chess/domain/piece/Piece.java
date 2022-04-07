package chess.domain.piece;

import chess.domain.board.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected static final String INVALID_DIRECTION = "진행할 수 없는 방향입니다.";
    protected static final String INVALID_POSITION = "진행할 수 없는 위치입니다.";

    private final PieceType pieceType;
    private final Color color;

    protected Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public abstract List<Position> calculatePathToValidate(final Position current, final Position target,
                                                           final Piece targetPiece);

    protected abstract Direction findValidDirection(Position current, Position target);

    protected abstract void validateDirection(Direction direction);

    public abstract boolean isEmpty();

    public boolean isSamePiece(final PieceType expected) {
        return pieceType == expected;
    }

    public boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public boolean hasSameColor(final Piece piece) {
        return piece.isSameColor(color);
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Piece)) {
            return false;
        }
        final Piece piece = (Piece) o;
        return Objects.equals(pieceType, piece.pieceType) && Objects.equals(color, piece.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType);
    }
}
