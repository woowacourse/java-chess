package chess.domain.location;

import chess.domain.board.Direction;
import java.util.Objects;

public class Location {

    private final Column column;
    private final Row row;

    public Location(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public Location move(Direction direction) {
        Column movedColumn = this.column.move(direction);
        Row movedRow = this.row.move(direction);
        return new Location(movedColumn, movedRow);
    }

    //TODO 예쁘게 바꾸기
    //TODO 캐싱

    public static Location of(String input) {
        try {
            validateInput(input);
            String columnInput = input.substring(0, 1);
            Column column = Column.of(columnInput);
            String rowInput = input.substring(1, 2);
            Row row = Row.of(rowInput);
            return new Location(column, row);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("잘못된 위치 입력입니다.");
        }
    }
    //TODO 예외 중복 처리

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("잘못된 위치 입력입니다.");
        }
        //TODO 상수 처리
        if (input.length() != 2) {
            throw new IllegalArgumentException("잘못된 위치 입력입니다.");
        }
    }

    public int calculateVerticalDistance(Location target) {
        return this.row.calculateDistance(target.row);
    }

    public int calculateHorizontalDistance(Location target) {
        return this.column.calculateDistance(target.column);
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
