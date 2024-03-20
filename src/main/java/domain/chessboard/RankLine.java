package domain.chessboard;

import java.util.List;

public class RankLine {
    private final List<Square> squares;

    public RankLine(final RankGenerator rankGenerator, final int row) {
        this.squares = rankGenerator.generate(row);
    }

    public int size() {
        return squares.size();
    }

    public List<Square> getSquares() {
        return squares;
    }
}
