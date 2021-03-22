package chess.domain.position;

public final class PositionName {

//    private final Column column;
//    private final Row row;
    private final String name;

    public PositionName(String positionName) {
        validate(positionName);
        this.name = positionName;
    }

    public PositionName(Column2 column, Row2 row) {
//        this.column = column;
//        this.row = row;
        this(column.value() + row.value());
    }

    private void validate(String positionName) {

    }
}
