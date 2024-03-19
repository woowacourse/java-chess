package chess.domain.position;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(rowPosition, position.rowPosition) && Objects.equals(columnPosition,
                position.columnPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowPosition, columnPosition);
    }
}
