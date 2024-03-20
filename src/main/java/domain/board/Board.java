package domain.board;

import domain.piece.Piece;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public static Board create() {
        SquaresGenerator squaresGenerator = new SquaresGenerator();
        Map<Position, Piece> squares = squaresGenerator.generate();
        return new Board(squares);
    }

    public List<Piece> extractPieces() {
        return squares.values().stream().toList();
    }
}
