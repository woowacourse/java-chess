package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

public class TestBoardGenerator implements BoardGenerator {

    private final Map<Point, Piece> custom;

    public TestBoardGenerator(Map<Point, Piece> custom) {
        this.custom = new HashMap<>(custom);
    }

    @Override
    public Map<Point, Piece> generate() {
        for (int verticalIndex = LineNumber.MIN; verticalIndex <= LineNumber.MAX; verticalIndex++) {
            generateLine(verticalIndex);
        }
        return Map.copyOf(custom);
    }

    private void generateLine(int verticalIndex) {
        for (int horizontalIndex = LineNumber.MIN; horizontalIndex <= LineNumber.MAX; horizontalIndex++) {
            Point point = Point.of(verticalIndex, horizontalIndex);
            custom.computeIfAbsent(point, ignored -> Empty.getInstance());
        }
    }
}
