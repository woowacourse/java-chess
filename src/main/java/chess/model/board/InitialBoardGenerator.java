package chess.model.board;

import chess.model.piece.*;
import chess.model.position.Position;

import java.util.HashMap;
import java.util.Map;

public class InitialBoardGenerator {
    private static final Map<Position, Piece> squares = new HashMap<>();
    private static final Color START_COLOR = Color.WHITE;

    static {
        squares.put(Position.of(1, 8), Rook.from(Color.BLACK));
        squares.put(Position.of(2, 8), Knight.from(Color.BLACK));
        squares.put(Position.of(3, 8), Bishop.from(Color.BLACK));
        squares.put(Position.of(4, 8), Queen.from(Color.BLACK));
        squares.put(Position.of(5, 8), King.from(Color.BLACK));
        squares.put(Position.of(6, 8), Bishop.from(Color.BLACK));
        squares.put(Position.of(7, 8), Knight.from(Color.BLACK));
        squares.put(Position.of(8, 8), Rook.from(Color.BLACK));
        for (int file = Board.MIN_LENGTH; file <= Board.MAX_LENGTH; file++) {
            squares.put(Position.of(file, 7), Pawn.from(Color.BLACK));
            squares.put(Position.of(file, 2), Pawn.from(Color.WHITE));
        }
        squares.put(Position.of(1, 1), Rook.from(Color.WHITE));
        squares.put(Position.of(2, 1), Knight.from(Color.WHITE));
        squares.put(Position.of(3, 1), Bishop.from(Color.WHITE));
        squares.put(Position.of(4, 1), Queen.from(Color.WHITE));
        squares.put(Position.of(5, 1), King.from(Color.WHITE));
        squares.put(Position.of(6, 1), Bishop.from(Color.WHITE));
        squares.put(Position.of(7, 1), Knight.from(Color.WHITE));
        squares.put(Position.of(8, 1), Rook.from(Color.WHITE));
    }

    public Board create() {
        return new Board(squares, START_COLOR);
    }
}
