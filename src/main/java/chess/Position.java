package chess;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position {

    private static final List<Position> cachedPositions;
    
    static {
        cachedPositions = IntStream.rangeClosed(0, 7)
                .mapToObj(row -> IntStream.rangeClosed(0, 7)
                        .mapToObj(column -> new Position(row, column)))
                .flatMap(Function.identity())
                .collect(Collectors.toList());
    }

    private static final List<Position> KING = List.of(valueOf(0, 4), valueOf(7, 3));
    private static final List<Position> QUEEN = List.of(valueOf(0, 3), valueOf(7, 4));
    private static final List<Position> KNIGHT = List.of(
            valueOf(0, 1),
            valueOf(0, 6),
            valueOf(7, 6),
            valueOf(7, 1));
    private static final List<Position> Rook = List.of(
            valueOf(0, 0),
            valueOf(0, 7),
            valueOf(7, 7),
            valueOf(7, 0)
    );
    private static final List<Position> BISHOP = List.of(
            valueOf(0, 2),
            valueOf(0, 5),
            valueOf(7, 2),
            valueOf(7, 5)
    );
    private static final List<Position> PAWN = List.of(
            valueOf(1, 0),
            valueOf(1, 1),
            valueOf(1, 2),
            valueOf(1, 3),
            valueOf(1, 4),
            valueOf(1, 5),
            valueOf(1, 6),
            valueOf(1, 7),
            valueOf(6, 0),
            valueOf(6, 1),
            valueOf(6, 2),
            valueOf(6, 3),
            valueOf(6, 4),
            valueOf(6, 5),
            valueOf(6, 6),
            valueOf(6, 7)
    );

    private int row;
    private int column;

    public Position(final int row, final int column) {
        validatePosition(row, column);
        this.row = row;
        this.column = column;
    }

    public static Position valueOf(int row, int column) {
        return cachedPositions.stream()
                .filter(position -> position.row == row && position.column == column)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스판에 존재하지 않는 위치값입니다."));
    }

    public boolean isKingPosition(final Position position) {
        return KING.contains(position);
    }

    public boolean isQueenPosition(final Position position) {
        return QUEEN.contains(position);
    }

    public boolean isKnightPosition(final Position position) {
        return KNIGHT.contains(position);
    }

    public boolean isRookPosition(final Position position) {
        return Rook.contains(position);
    }

    public boolean isBishopPosition(final Position position) {
        return BISHOP.contains(position);
    }

    public boolean isPawnPosition(final Position position) {
        return PAWN.contains(position);
    }

    private void validatePosition(final int row, final int column) {
        if (row < 0 || column < 0 || row > 7 || column > 7) {
            throw new IllegalArgumentException(
                    MessageFormat.format("체스판 범위에서 벗어났습니다. row: {0}, column: {1}", row, column)
            );
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        final Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
