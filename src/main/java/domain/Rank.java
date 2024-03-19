package domain;

import java.util.List;

public class Rank {
    private final List<Square> squares;

    public Rank(final RankGenerator rankGenerator, final int index) {
        this.squares = rankGenerator.generate(index);
    }

    public int size() {
        return squares.size();
    }
}
