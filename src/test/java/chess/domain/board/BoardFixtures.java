package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class BoardFixtures {

    public static Board empty() {
        return Board.of(new TestBoardGenerator(new HashMap<>()));
    }

    public static Board initial() {
        return Board.of(new InitialBoardGenerator());
    }

    public static Board create(Map<Point, Piece> pointPieces) {
        return Board.of(new TestBoardGenerator(pointPieces));
    }
}
