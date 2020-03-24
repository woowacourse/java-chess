package chess.domain;

public class Point {
    private String x;
    private String y;
    private Piece piece;

    public Point(String x, String y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }
}
