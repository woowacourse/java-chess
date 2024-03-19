package chess.domain.position;

public class Position {
    //TODO: ROW 클래스 이름 수정
    //TODO: 포지션 캐싱
    //TODO: 포지션 관련 클래스들 이퀄스 해시 재정의

    private final RowPosition rowPosition;
    private final ColumnPosition columnPosition;

    public static Position of(int rowPosition, int colPosition) {
        return new Position(new RowPosition(rowPosition), new ColumnPosition(colPosition));
    }

    public Position(RowPosition rowPosition, ColumnPosition columnPosition) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
    }
}
