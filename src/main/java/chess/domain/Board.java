package chess.domain;

import java.util.Map;

public class Board {

    public static final int LOWER_BOUNDARY = 1;
    public static final int UPPER_BOUNDARY = 8;

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Piece getPiece(final File file, final Rank rank) {
        return board.get(Position.of(file, rank));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
