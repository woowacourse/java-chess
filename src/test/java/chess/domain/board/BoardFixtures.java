package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class BoardFixtures {

    public static final Board INITIAL_BOARD = Board.of(new InitialBoardGenerator());
    public static final Board ROOK_NOT_MOVABLE_BOARD = Board.of(new TestBoardGenerator(
            Map.of(Point.of(1, 1), new Rook(Color.WHITE),
                    Point.of(1,6), new Pawn(Color.BLACK))));
    public static final Board BISHOP_MOVABLE_BOARD = Board.of(new TestBoardGenerator(
            Map.of(Point.of(1, 1), new Bishop(Color.WHITE))));


    public static Board create(Map<Point, Piece> pointPieces) {
        return Board.of(new TestBoardGenerator(pointPieces));
    }

    static class TestBoardGenerator implements BoardGenerator {

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
                custom.computeIfAbsent(point, ignored -> new Empty(Color.NONE));
            }
        }
    }
}
