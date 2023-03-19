package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.Direction;
import domain.type.PieceType;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.PieceView;

public abstract class Piece {

    protected static final String IMPOSSIBLE_MOVE_ERROR_MESSAGE = "가 이동할 수 없는 위치입니다.";
    protected final Color color;
    protected final PieceType pieceType;

    protected Piece(final Color color, final PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public static Piece getEmpty() {
        return new EmptyPiece();
    }

    abstract protected boolean isNotMovable(final Location start, final Location end);
    
    public List<Location> searchPath(final Location start, final Location end) {
        final Direction direction = Direction.find(start, end);
        if (direction.equals(Direction.ELSE) || isNotMovable(start, end)) {
            throw new IllegalArgumentException(PieceView.findSign(this) + IMPOSSIBLE_MOVE_ERROR_MESSAGE);
        }
        final int totalMove = Math.max(
            Math.abs(start.getCol() - end.getCol()), Math.abs(start.getRow() - end.getRow()));
        return IntStream.range(1, totalMove + 1)
            .mapToObj(
                count -> Location.of(
                    start.getCol() + (direction.getColDiff() * count),
                    start.getRow() + (direction.getRowDiff()) * count))
            .collect(Collectors.toList());
    }

    public boolean isSameType(final PieceType pieceType) {
        return this.pieceType.equals(pieceType);
    }

    public boolean isDifferentType(final PieceType pieceType) {
        return !this.pieceType.equals(pieceType);
    }

    public boolean isEnemy(final Piece piece) {
        return piece.isDifferentType(PieceType.EMPTY) && isDifferentColor(piece);
    }

    public boolean isDifferentColor(final Piece piece) {
        return !this.color.equals(piece.color);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
