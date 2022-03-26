package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class BoardFixtures {

    public static final Board INITIAL_BOARD = Board.of(new InitialBoardGenerator());
    public static final Board EMPTY_BOARD = Board.of(new TestBoardGenerator(new HashMap<>()));

    public static final Board ROOK_NOT_MOVABLE_BOARD = Board.of(new TestBoardGenerator(
            Map.of(Point.of(1, 1), new Rook(Color.WHITE),
                    Point.of(1,6), new Pawn(Color.BLACK))));
    public static final Board BISHOP_MOVABLE_BOARD = Board.of(new TestBoardGenerator(
            Map.of(Point.of(1, 1), new Bishop(Color.WHITE))));


    public static Board create(Map<Point, Piece> pointPieces) {
        return Board.of(new TestBoardGenerator(pointPieces));
    }
}
