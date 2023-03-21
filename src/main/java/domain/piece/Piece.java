package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.PieceType;
import domain.type.direction.PieceMoveDirection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.PieceView;

public abstract class Piece {

    private static final int FIRST_MOVE = 1;
    private static final String IMPOSSIBLE_MOVE_ERROR_MESSAGE = "가 이동할 수 없는 위치입니다.";
    private final Color color;
    private final PieceType pieceType;
    private final BiPredicate<Location, Location> moveCheckStrategy;

    protected Piece(Color color, PieceType pieceType, BiPredicate<Location, Location> moveCheckStrategy) {
        this.color = color;
        this.pieceType = pieceType;
        this.moveCheckStrategy = moveCheckStrategy;
    }

    public List<Location> searchPath(final Location start, final Location end) {
        final PieceMoveDirection direction = PieceMoveDirection.find(start, end);
        if (!moveCheckStrategy.test(start, end)) {
            throw new IllegalArgumentException(PieceView.findSign(this) + IMPOSSIBLE_MOVE_ERROR_MESSAGE);
        }
        final int lastMove = getTotalMoveCount(start, end) + 1;
        return IntStream.range(FIRST_MOVE, lastMove)
            .mapToObj(
                count -> Location.of(
                    start.getCol() + (direction.getColDiff() * count),
                    start.getRow() + (direction.getRowDiff()) * count))
            .collect(Collectors.toList());
    }

    private int getTotalMoveCount(final Location start, final Location end) {
        PieceMoveDirection pieceMoveDirection = PieceMoveDirection.find(start, end);
        int moveCount = 1;
        Location current = start;
        while (!current.addDirectionOnce(pieceMoveDirection).equals(end)) {
            moveCount++;
            current = current.addDirectionOnce(pieceMoveDirection);
        }
        return moveCount;
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
