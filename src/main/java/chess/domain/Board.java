package chess.domain;


import java.util.ArrayList;
import java.util.List;

class Board {
    private static final List<Point> POINTS = new ArrayList<>();
    private static final int SIZE = 64;
    private static final String INVALID_SIZE = "옳바르지 않은 갯수입니다.";

    private Board() {};

    static Board of() {
        if (POINTS.size() != SIZE) {
            throw new IllegalStateException(INVALID_SIZE);
        }
        return new Board();
    }
    static {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                POINTS.add(new Point(String.valueOf(i), String.valueOf(j), new Blank(PieceType.BLANK)));
            }
        }
    }

    int size() {
        return POINTS.size();
    }
}
