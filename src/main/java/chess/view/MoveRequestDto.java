package chess.view;

public class MoveRequestDto {

    private final String fromColumn;
    private final String fromRow;
    private final String toColumn;
    private final String toRow;

    public MoveRequestDto(String fromColumn, String fromRow, String toColumn, String toRow) {
        this.fromColumn = fromColumn;
        this.fromRow = fromRow;
        this.toColumn = toColumn;
        this.toRow = toRow;
    }

    public String getFromColumn() {
        return fromColumn;
    }

    public String getFromRow() {
        return fromRow;
    }

    public String getToColumn() {
        return toColumn;
    }

    public String getToRow() {
        return toRow;
    }
}
