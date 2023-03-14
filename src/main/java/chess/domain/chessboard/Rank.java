package chess.domain.chessboard;

import java.util.ArrayList;
import java.util.List;

public final class Rank {

    private static final int FIXED_ONE_RANK_SIZE = 8;
    private final List<Square> squares = new ArrayList<>();

    public Rank() {
        initSquares();
    }

    private void initSquares() {
        for (int squareIndex = 0; squareIndex < FIXED_ONE_RANK_SIZE; squareIndex++) {
            squares.add(new Square());
        }
    }
}
