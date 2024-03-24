package chess.model.board;

import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.piece.PieceFactory;
import chess.model.piece.Type;
import chess.model.position.Position;

import java.util.HashMap;
import java.util.Map;

public class InitialBoardGenerator {
    private static final Map<Position, Piece> squares = new HashMap<>();

    static {
        squares.put(Position.of(1, 8), PieceFactory.of(Color.BLACK, Type.ROOK));
        squares.put(Position.of(2, 8), PieceFactory.of(Color.BLACK, Type.KNIGHT));
        squares.put(Position.of(3, 8), PieceFactory.of(Color.BLACK, Type.BISHOP));
        squares.put(Position.of(4, 8), PieceFactory.of(Color.BLACK, Type.QUEEN));
        squares.put(Position.of(5, 8), PieceFactory.of(Color.BLACK, Type.KING));
        squares.put(Position.of(6, 8), PieceFactory.of(Color.BLACK, Type.BISHOP));
        squares.put(Position.of(7, 8), PieceFactory.of(Color.BLACK, Type.KNIGHT));
        squares.put(Position.of(8, 8), PieceFactory.of(Color.BLACK, Type.ROOK));
        for (int file = Board.MIN_LENGTH; file <= Board.MAX_LENGTH; file++) {
            squares.put(Position.of(file, 7), PieceFactory.of(Color.BLACK, Type.PAWN));
            squares.put(Position.of(file, 2), PieceFactory.of(Color.WHITE, Type.PAWN));
        }
        squares.put(Position.of(1, 1), PieceFactory.of(Color.WHITE, Type.ROOK));
        squares.put(Position.of(2, 1), PieceFactory.of(Color.WHITE, Type.KNIGHT));
        squares.put(Position.of(3, 1), PieceFactory.of(Color.WHITE, Type.BISHOP));
        squares.put(Position.of(4, 1), PieceFactory.of(Color.WHITE, Type.QUEEN));
        squares.put(Position.of(5, 1), PieceFactory.of(Color.WHITE, Type.KING));
        squares.put(Position.of(6, 1), PieceFactory.of(Color.WHITE, Type.BISHOP));
        squares.put(Position.of(7, 1), PieceFactory.of(Color.WHITE, Type.KNIGHT));
        squares.put(Position.of(8, 1), PieceFactory.of(Color.WHITE, Type.ROOK));
    }

    public Board create() {
        return new Board(squares);
    }
}
