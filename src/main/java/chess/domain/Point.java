package chess.domain;

import chess.domain.piece.Direction;
import chess.domain.piece.kind.Piece;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Point {
    private static final int ASCII_CODE_GAP = 97;
    private static final int RANK_GAP = 8;
    private static final char MINIMUM_LETTER = 'a';
    private static final char MAXIMUM_LETTER = 'h';
    private static final int MINIMUM_RANK = 1;
    private static final int MAXIMUM_RANK = 8;
    private static final int LETTER_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private static final List<Point> points;

    static {
        points = IntStream.rangeClosed(MINIMUM_LETTER, MAXIMUM_LETTER)
                .mapToObj(i -> (char) i)
                .flatMap(row ->
                        IntStream.rangeClosed(MINIMUM_RANK, MAXIMUM_RANK)
                                .mapToObj(column -> new Point(row, column))
                )
                .collect(Collectors.toList());
    }

    private final int row;
    private final int column;

    private Point(char letter, int rank) {
        row = convertRankToIndex(rank);
        column = convertLetterToIndex(letter);
    }

    public static Point of(String point) {
        return of(point.charAt(LETTER_INDEX), Character.getNumericValue(point.charAt(RANK_INDEX)));
    }

    private static Point of(char letter, int rank) {
        return points.stream()
                .filter(p -> p.isSameColumn(p.convertLetterToIndex(letter)) && p.isSameRow(p.convertRankToIndex(rank)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 좌표입니다."));
    }

    public static Point of(int row, int column) {
        return points.stream()
                .filter(p -> p.isSameColumn(column) && p.isSameRow(row))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 좌표입니다."));
    }

    private int convertLetterToIndex(char letter) {
        return letter - ASCII_CODE_GAP;
    }

    private int convertRankToIndex(int rank) {
        return RANK_GAP - rank;
    }

    public int calculateDistance(Point point) {
        return ((int) Math.pow(this.subtractRow(point), 2) + (int) Math.pow(this.subtractColumn(point), 2));
    }

    public Point createNextPoint(Direction direction) {
        return of(direction.addCurrentRow(this.row), direction.addCurrentColumn(this.column));
    }

    public boolean isSamePoint(Point target) {
        return this.equals(target);
    }

    public boolean isSameRow(int row) {
        return this.row == row;
    }

    public boolean isSameColumn(int column) {
        return this.column == column;
    }

    public int subtractRow(Point point) {
        return this.row - point.row;
    }

    public int subtractColumn(Point point) {
        return this.column - point.column;
    }

    public Direction findDirection(Point target) {
        int initialRowDifference = target.subtractRow(this);
        int initialColumnDifference = target.subtractColumn(this);

        if (initialRowDifference == 0 && initialColumnDifference == 0) {
            throw new IllegalArgumentException("기물이 움직이지 않습니다.");
        }

        return Direction.createDirection(initialRowDifference, initialColumnDifference);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return column == point.column &&
                row == point.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
