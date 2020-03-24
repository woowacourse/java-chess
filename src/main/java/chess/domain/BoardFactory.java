package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory {
    private static final List<Point> POINTS = new ArrayList<>();

    static {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                POINTS.add(new Point(String.valueOf(i), String.valueOf(j), new Blank(PieceType.BLANK)));
            }
        }
    }

    static Board create() {
        return new Board(POINTS);
    }
}
