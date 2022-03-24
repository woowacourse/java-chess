package chess.domain;

public class Position {
    private final Column col;
    private final Row row;

    public Position(Column col, Row row) {
        this.col = col;
        this.row = row;
    }

    public static Position from(String position) throws RuntimeException{
        validateLength(position);
        Column col = Column.find(position.charAt(0));
        Row row = Row.find(Character.getNumericValue(position.charAt(1)));
        return new Position(col, row);
    }

    private static void validateLength(String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException("올바른 포지션 값이 아닙니다.");
        }
    }

    public Column getCol() {
        return col;
    }

    public Row getRow() {
        return row;
    }
}
