package chess.position;

public class Position {
    //TODO: ROW 클래스 이름 수정
    //TODO: 포지션 캐싱

    private final Row row;
    private final Column column;

    public Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }
}
