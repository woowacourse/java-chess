package chess.domain.position;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position {
    private static final int MIN_LIMIT = 1;
    private static final int MAX_LIMIT = 8;
    private static final int SQUARE_UNIT = 2;
    private static final int FIRST_ROW_FOR_WHITE_PAWN = 2;
    private static final int FIRST_ROW_FOR_BLACK_PAWN = 7;
    private static final int TARGET_INDEX = 1;
    private static final int SOURCE_INDEX = 1;
    private static final int MINIMUM_DUPLICATES_COUNT = 2;
    private static final int EACH_SAME = 0;

    private final int x;
    private final int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
        validatePosition();
    }

    private void validatePosition() {
        if (isOutOfRange(x) || isOutOfRange(y)) {
            throw new IllegalArgumentException("체스판을 넘었습니다.");
        }
    }

    private boolean isOutOfRange(int number) {
        return number < MIN_LIMIT || number > MAX_LIMIT;
    }

    public boolean canMoveBackAndForth(Position position) {
        return this.x == position.x;
    }

    public boolean canMoveSideToSide(Position position) {
        return this.y == position.y;
    }

    public boolean canMovePositiveDiagonally(Position position) {
        return subtractY(position) == subtractX(position);
    }

    public boolean canMoveNegativeDiagonally(Position position) {
        return subtractY(position) + subtractX(position) == EACH_SAME;
    }

    public int getDistanceSquare(Position position) {
        return (int) (Math.pow(subtractY(position), SQUARE_UNIT) + Math.pow(subtractX(position), SQUARE_UNIT));
    }

    private int subtractX(Position position) {
        return this.x - position.x;
    }

    public int subtractY(Position position) {
        return this.y - position.y;
    }

    public boolean isInStartingPosition() {
        return (this.y == FIRST_ROW_FOR_WHITE_PAWN) || (this.y == FIRST_ROW_FOR_BLACK_PAWN);
    }

    public List<Position> getRoutePosition(Position position) {
        List<Position> routePositions = new ArrayList<>();

        List<Integer> xValues = getRouteRange(this.x, position.x);
        List<Integer> yValues = getRouteRange(this.y, position.y);

        if (canMoveSideToSide(position)) {
            routePositions.addAll(getSideToSideRoute(xValues));
        }

        if (canMoveBackAndForth(position)) {
            routePositions.addAll(getBackAndForthRoute(yValues));
        }

        if (canMovePositiveDiagonally(position)) {
            routePositions.addAll(getPositiveDiagonally(xValues, yValues));
        }

        if (canMoveNegativeDiagonally(position)) {
            routePositions.addAll(getNegativeDiagonallyRoute(xValues, yValues));
        }

        return routePositions;
    }

    private List<Integer> getRouteRange(int firstCoordinate, int secondCoordinate) {
        return IntStream.rangeClosed(getRangeStart(firstCoordinate, secondCoordinate), getRangeEnd(firstCoordinate, secondCoordinate))
                .boxed()
                .collect(Collectors.toList());
    }

    private int getRangeStart(int first, int second) {
        return Math.min(first, second) + SOURCE_INDEX;
    }

    private int getRangeEnd(int first, int second) {
        return Math.max(first, second) - TARGET_INDEX;
    }

    private List<Position> getSideToSideRoute(List<Integer> xValues) {
        return xValues.stream()
                .map(x -> new Position(x, y))
                .collect(Collectors.toList());
    }

    private List<Position> getBackAndForthRoute(List<Integer> yValues) {
        return yValues.stream()
                .map(y -> new Position(x, y))
                .collect(Collectors.toList());
    }

    private List<Position> getPositiveDiagonally(List<Integer> xValues, List<Integer> yValues) {
        List<Position> routePositions = new ArrayList<>();

        for (int i = 0; i < xValues.size(); i++) {
            routePositions.add(new Position(xValues.get(i), yValues.get(i)));
        }

        return routePositions;
    }

    private List<Position> getNegativeDiagonallyRoute(List<Integer> xValues, List<Integer> yValues) {
        List<Integer> reversedYValues = new ArrayList<>(yValues);
        Collections.reverse(reversedYValues);

        List<Position> routePositions = new ArrayList<>();

        for (int i = 0; i < xValues.size(); i++) {
            routePositions.add(new Position(xValues.get(i), reversedYValues.get(i)));
        }

        return routePositions;
    }

    public static double getDuplicatedItemsCount(List<Position> pawnPosition) {
        List<Integer> xValuesPawnHas = pawnPosition.stream()
                .map(position -> position.x)
                .collect(Collectors.toList());

        Set<Integer> unique_x = new HashSet<>(xValuesPawnHas);

        return unique_x.stream()
                .map(x -> Collections.frequency(xValuesPawnHas, x))
                .filter(count -> count >= MINIMUM_DUPLICATES_COUNT)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public boolean isMatchPosition(int x, int y) {
        return (this.x == x) && (this.y == y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}