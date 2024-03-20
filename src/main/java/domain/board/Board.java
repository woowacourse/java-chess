package domain.board;

import java.util.List;

public class Board {

    private final List<Square> squares;

    public Board(List<Square> squares) {
        this.squares = squares;
    }

    public static Board create() {
        SquaresGenerator squaresGenerator = new SquaresGenerator();
        List<Square> squares = squaresGenerator.generate();
        return new Board(squares);
    }
}
