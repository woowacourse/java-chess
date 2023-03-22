package domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum PositionFactory {

    ZERO('a', '8', 0),
    ONE('b', '7', 1),
    TWO('c', '6', 2),
    THREE('d', '5', 3),
    FOUR('e', '4', 4),
    FIVE('f', '3', 5),
    SIX('g', '2', 6),
    SEVEN('h', '1', 7);

    private static final int INDEX_COLUMN = 0;
    private static final int INDEX_ROW = 1;
    private static final int MAX_SIZE = 8;

    private final char column;
    private final char row;
    private final int value;

    PositionFactory(char column, char row, int value) {
        this.column = column;
        this.row = row;
        this.value = value;
    }

    public static Position createPosition(String command) {
        return Position.of(findColumn(command), findRow(command));
    }

    public static List<Position> findRow(final int row) {
        List<Position> positions = new ArrayList<>();

        for (int column = 0; column < MAX_SIZE; column++) {
            positions.add(Position.of(column, row));
        }

        return positions;
    }

    public static List<Position> findColumn(final int column) {
        List<Position> positions = new ArrayList<>();

        for (int row = 0; row < MAX_SIZE; row++) {
            positions.add(Position.of(column, row));
        }

        return positions;
    }

    private static int findColumn(final String command) {
        final char coordinate = getCoordinate(command, INDEX_COLUMN);
        return PositionFactory.from(coordinate, value -> value.column).value;
    }

    private static int findRow(final String command) {
        final char coordinate = getCoordinate(command, INDEX_ROW);
        return PositionFactory.from(coordinate, value -> value.row).value;
    }

    private static char getCoordinate(final String command, final int index) {
        return command.toLowerCase().charAt(index);
    }

    private static PositionFactory from(char coordinate, Function<PositionFactory, Character> findCoordinate) {
        return Arrays.stream(values())
                .filter(value -> findCoordinate.apply(value) == coordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("범위 내의 값만 입력해주세요."));
    }

}
