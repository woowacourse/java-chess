package chess.domain.location;

import chess.domain.board.Direction;
import java.util.Objects;

//TODO 캐싱
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

    public static Location of(String input) {
        validateInput(input);
        Column column = Column.findByName(input.substring(0, 1));
        Row row = Row.findByRank(input.substring(1, 2));
        return new Location(column, row);
    }

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("빈 값을 위치로 입력할 수 없습니다.");
        }
        if (input.length() != 2) {
            throw new IllegalArgumentException("잘못된 위치 입력입니다. 입력 형식 : A~H 사이의 알파벳 + 1~8 사이의 숫자");
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
