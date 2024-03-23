package chess.view;

public class MoveRequestDto {

    String fromColumn;
    String fromRow;
    String toColumn;
    String toRow;

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
