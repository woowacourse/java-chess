package chess.model.board;

import chess.model.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class InitialBoardGenerator implements BoardGenerator {
    private static final Map<Position, Piece> squares = new HashMap<>();

    static {
        squares.put(Position.from(1, 8), Piece.BLACK_ROOK);
        squares.put(Position.from(2, 8), Piece.BLACK_KNIGHT);
        squares.put(Position.from(3, 8), Piece.BLACK_BISHOP);
        squares.put(Position.from(4, 8), Piece.BLACK_QUEEN);
        squares.put(Position.from(5, 8), Piece.BLACK_KING);
        squares.put(Position.from(6, 8), Piece.BLACK_BISHOP);
        squares.put(Position.from(7, 8), Piece.BLACK_KNIGHT);
        squares.put(Position.from(8, 8), Piece.BLACK_ROOK);
        for (int x = 1; x <= 8; x++) {
            squares.put(Position.from(x, 7), Piece.BLACK_PAWN);
        }
        for (int x = 1; x <= 8; x++) {
            squares.put(Position.from(x, 2), Piece.WHITE_PAWN);
        }
        squares.put(Position.from(1, 1), Piece.WHITE_ROOK);
        squares.put(Position.from(2, 1), Piece.WHITE_KNIGHT);
        squares.put(Position.from(3, 1), Piece.WHITE_BISHOP);
        squares.put(Position.from(4, 1), Piece.WHITE_QUEEN);
        squares.put(Position.from(5, 1), Piece.WHITE_KING);
        squares.put(Position.from(6, 1), Piece.WHITE_BISHOP);
        squares.put(Position.from(7, 1), Piece.WHITE_KNIGHT);
        squares.put(Position.from(8, 1), Piece.WHITE_ROOK);
    }

    @Override
    public Board create() {
        return new Board(squares);
    }
}
