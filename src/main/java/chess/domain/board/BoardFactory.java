package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BoardFactory {
    private BoardFactory() {
        throw new AssertionError();
    }

    public static Board create(List<String> arrange) {
        checkBoardSize(arrange.size());

        Map<Point, Optional<Piece>> points = new HashMap<>();

        for (int i = 0; i <= Coordinate.MAX_COORDINATE; i++) {
            String[] row = arrange.get(i).split("");
            checkBoardSize(row.length);
            for (int j = 0; j <= Coordinate.MAX_COORDINATE; j++) {
                points.put(Point.of(j, i), PieceType.of(row[j]).create(PlayerType.of(row[j])));
            }
        }
        return new Board(points);
    }

    private static void checkBoardSize(int size) {
        if (size != Board.BOUNDARY) {
            throw new IllegalArgumentException();
        }
    }
}
