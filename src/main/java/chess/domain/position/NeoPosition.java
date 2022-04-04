package chess.domain.position;

public class NeoPosition {

    private final int id;
    private final Column column;
    private final Row row;
    private final int boardId;

    public NeoPosition(int id, Column column, Row row, int boardId) {
        this.id = id;
        this.column = column;
        this.row = row;
        this.boardId = boardId;
    }

    public NeoPosition(Column column, Row row, int boardId) {
        this(0, column, row, boardId);
    }

    public int getId() {
        return id;
    }

    public Column getColumn() {
        return column;
    }

    public Row getRow() {
        return row;
    }

    public int getBoardId() {
        return boardId;
    }
}
