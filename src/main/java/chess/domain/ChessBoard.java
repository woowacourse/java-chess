package chess.domain;

import java.util.List;

public class ChessBoard {

    private final List<Square> squares;

    ChessBoard(List<Square> squares) {
        this.squares = squares;
    }

    public List<Square> getSquares() {
        return squares;
    }
}
