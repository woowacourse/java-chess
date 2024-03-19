package chess.domain.position;

public class Position {
    //TODO: ROW 클래스 이름 수정
    //TODO: 포지션 캐싱

    private final RowPosition rowPosition;
    private final ColumnPosition columnPosition;

    public Position(RowPosition rowPosition, ColumnPosition columnPosition) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
    }
}
