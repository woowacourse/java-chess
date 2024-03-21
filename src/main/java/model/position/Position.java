package model.position;

import java.util.Objects;

public class Position {

    private final Column column;
    private final Row row;

    public Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position from(String command) {
        validate(command);
        return new Position(Column.from(command.charAt(0)), Row.from(command.charAt(1)));
    }

    private static void validate(final String command) {
        if (command == null || command.isBlank()) {
            throw new IllegalArgumentException("공백이 입력되었습니다."); //TODO 예외 메시지 수정
        }
        if (command.length() != 2) {
            throw new IllegalArgumentException("유효하지 않은 길이의 명령입니다.");
        }
    }

    public int getRowIndex() {
        return row.getIndex();
    }

    public int getColumnIndex() {
        return column.getIndex();
    }

    public Column getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Position position)) {
            return false;
        }
        return Objects.equals(row, position.row) && Objects.equals(column, position.column);
    }

    @Override
    public String toString() {
        return column.getValue() + row.getValue() + "  (" + row.getIndex() + ", " + column.getIndex() + ")";
    }
}
