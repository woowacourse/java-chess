package chess;

public class Position {
    private final Row row;
    private final Column column;

    public Position(String position) {
        row=Row.valueOf(position.substring(0, 1));
        column = Column.valueOf(position.substring(1));
    }
}
