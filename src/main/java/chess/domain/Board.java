package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import java.util.Map;

public class Board {

    public static final int LOWER_BOUNDARY = 1;
    public static final int UPPER_BOUNDARY = 8;

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public void move(final Position source, final Position target) {
        final Piece sourcePiece = board.get(source);
        board.put(target, sourcePiece);
        board.put(source, new Empty(Type.EMPTY, Side.NEUTRALITY));
    }

    public Side findSideByPosition(final Position position) {
        final Piece piece = board.get(position);
        return piece.getSide();
    }

    public Piece getPiece(final File file, final Rank rank) {
        return board.get(Position.of(file, rank));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
