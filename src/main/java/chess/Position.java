package chess;

public record Position(
    Column column,
    Row row
) {

    public Position(
        final Row row,
        final Column column
    ) {
        this(column, row);
    }

    public Position move(final Movement movement) {
        final int x = movement.x();
        final int y = movement.y();
        return new Position(
            row.move(y),
            column.move(x)
        );
    }
}
