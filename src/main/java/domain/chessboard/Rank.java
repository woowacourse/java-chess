package domain.chessboard;

import java.util.List;

public class Rank {
    private final List<Square> squares;

    public Rank(final RankGenerator rankGenerator, final int row) {
        this.squares = rankGenerator.generate(row);
    }

    public int size() {
        return squares.size();
    }

    public List<Square> getSquares() {
        return squares;
    }
}
