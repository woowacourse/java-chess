package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class TestBoardGenerator implements BoardGenerator {

    private final Map<Point, Piece> custom;

    public TestBoardGenerator(Map<Point, Piece> custom) {
        this.custom = new HashMap<>(custom);
    }

    @Override
    public Map<Point, Piece> generate() {
        for (int i = 1; i <= 8; i++) {
            generateLine(i);
        }
        return Map.copyOf(custom);
    }

    private void generateLine(int i) {
        for (int j = 1; j <= 8; j++) {
            Point point = Point.of(i, j);
            custom.computeIfAbsent(point, ignored -> new Empty());
        }
    }
}
