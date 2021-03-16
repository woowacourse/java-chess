package domain;

public class Position {
    private final Column column;
    private final Row row;

    public Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }
}
