package chess.domain.board;

public class Point {
    private Coordinate x;
    private Coordinate y;

    public Point(int x, int y) {
        this.x = Coordinate.of(x);
        this.y = Coordinate.of(y);
    }




}
