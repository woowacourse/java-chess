package chess.domain;


import java.util.List;

class Board {
    private static final int SIZE = 64;
    private static final String INVALID_SIZE = "옳바르지 않은 갯수입니다.";

    private final List<Point> points;

    Board(List<Point> points) {
        if (points.size() != SIZE) {
            throw new IllegalStateException(INVALID_SIZE);
        }
        this.points = points;
    }

    int size() {
        return points.size();
    }
}
