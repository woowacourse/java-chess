package chess.domain.chessboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Rank {

    private static final int FIXED_ONE_RANK_SIZE = 8;
    private final List<Square> squares;

    public Rank() {
        squares = new ArrayList<>();
        initSquares(new Square());
    }

    public Rank(final Square square) {
        squares = new ArrayList<>();
        initSquares(square);
    }

    public Rank(final List<Square> squares) {
        this.squares = new ArrayList<>(squares);
        validateSize();
    }

    private void initSquares(final Square square) {
        for (int squareIndex = 0; squareIndex < FIXED_ONE_RANK_SIZE; squareIndex++) {
            squares.add(square);
        }
    }

    private void validateSize() {
        if (squares.size() != FIXED_ONE_RANK_SIZE) {
            throw new IllegalArgumentException("랭크는 8칸이어야 합니다.");
        }
    }

    public List<Square> getSquares() {
        return Collections.unmodifiableList(squares);
    }
}
