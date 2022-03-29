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

    public abstract Direction findValidDirection(Position current, Position target);

    public boolean isSamePiece(final PieceType expected) {
        return pieceType == expected;
    }

    public boolean hasSameColor(final Piece piece) {
        return isSameColor(piece.color);
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isEmpty() {
        return pieceType == PieceType.NONE;
    }

    protected void validateDirection(Direction direction, final List<Direction> possibleDirections) {
        if (!possibleDirections.contains(direction)) {
            throw new IllegalArgumentException(INVALID_DIRECTION);
        }
    }

    protected void validateRange(final int rowDifference, final int columnDifference, final int possibleDirection) {
        if (isInvalidRange(rowDifference, columnDifference, possibleDirection)) {
            throw new IllegalArgumentException(INVALID_POSITION);
        }
    }

    private boolean isInvalidRange(final int rowDifference, final int columnDifference, final int possibleDistance) {
        return Math.abs(rowDifference) > possibleDistance || Math.abs(columnDifference) > possibleDistance;
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
        Piece piece = (Piece) o;
        return Objects.equals(pieceType, piece.pieceType) && Objects.equals(color, piece.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType);
    }

}
