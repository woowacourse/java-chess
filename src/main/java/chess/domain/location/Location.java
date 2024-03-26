package chess.domain.location;

import chess.domain.board.Direction;
import java.util.Objects;

public class Location {
    private static final IllegalArgumentException WRONG_LOCATION_INPUT_EXCEPTION
            = new IllegalArgumentException("잘못된 위치 입력입니다.");
    private static final int LOCATION_INPUT_LENGTH = 2;
    public static final int COLUMN_INPUT_INDEX = 0;
    public static final int ROW_INPUT_INDEX = 1;

    private final Column column;
    private final Row row;

    public Location(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Location of(String input) {
        try {
            validateInput(input);
            Column column = Column.of(input.substring(COLUMN_INPUT_INDEX, ROW_INPUT_INDEX));
            Row row = Row.of(input.substring(ROW_INPUT_INDEX, LOCATION_INPUT_LENGTH));
            return new Location(column, row);
        } catch (IllegalArgumentException exception) {
            throw WRONG_LOCATION_INPUT_EXCEPTION;
        }
    }

    public Location move(Direction direction) {
        Column movedColumn = this.column.move(direction);
        Row movedRow = this.row.move(direction);
        return new Location(movedColumn, movedRow);
    }

    public int calculateVerticalDistance(Location target) {
        return this.row.calculateDistance(target.row);
    }

    public int calculateHorizontalDistance(Location target) {
        return this.column.calculateDistance(target.column);
    }

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw WRONG_LOCATION_INPUT_EXCEPTION;
        }
        if (input.length() != LOCATION_INPUT_LENGTH) {
            throw WRONG_LOCATION_INPUT_EXCEPTION;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return column == location.column && row == location.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
