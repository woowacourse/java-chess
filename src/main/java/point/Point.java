package point;

public class Point {

    //TODO : 64개 캐싱

    private final Row row;
    private final Column column;

    public Point(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    //TODO : equals, hashcode 구현
}
