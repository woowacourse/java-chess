package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

public class CustomBoardGenerator implements BoardGenerator {

    private final Map<Point, Piece> pointPieces;

    public CustomBoardGenerator(Map<Point, Piece> pointPieces) {
        this.pointPieces = new HashMap<>(pointPieces);
    }

    @Override
    public Map<Point, Piece> generate() {
        for (int verticalIndex = LineNumber.MAX; verticalIndex >= LineNumber.MIN; verticalIndex--) {
            generateLine(verticalIndex);
        }
        return Map.copyOf(pointPieces);
    }

    private void generateLine(int verticalIndex) {
        for (int horizontalIndex = LineNumber.MIN; horizontalIndex <= LineNumber.MAX; horizontalIndex++) {
            pointPieces.computeIfAbsent(Point.of(horizontalIndex, verticalIndex), ignored -> Empty.getInstance());
        }
    }
}
