package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class BoardFixtures {

    public static Board empty() {
        return new Board(TestBoardGenerator.generate(new HashMap<>()));
    }

    public static Board initial() {
        return new Board(InitialBoardGenerator.generate());
    }

    public static Board create(Map<Point, Piece> pointPieces) {
        return new Board(TestBoardGenerator.generate(pointPieces));
    }
}
