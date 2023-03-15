package techcourse.fp.chess.domain;

import java.util.Map;

public final class Board {

    public static final int BOARD_SIZE = 8;
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        validate(board);
        this.board = board;
    }

    private void validate(final Map<Position, Piece> board) {
        if (board.size() != Math.pow(BOARD_SIZE, 2)) {
            throw new IllegalArgumentException(String.format("체스판의 사이즈는 %d x %d 여야합니다.", BOARD_SIZE, BOARD_SIZE));
        }
    }
}
