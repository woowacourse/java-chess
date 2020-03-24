package chess.domain;

class Point {
    private final String x;
    private final String y;
    private final Piece piece;

    Point(String x, String y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }
}
