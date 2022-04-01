package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Piece;

public class BoardFixtures {

    public static final Board EMPTY = create(new HashMap<>());
    public static final Board INITIAL = Board.of(new InitialBoardGenerator());

    public static Board create(Map<Point, Piece> pointPieces) {
        return Board.of(new TestBoardGenerator(pointPieces));
    }
}
