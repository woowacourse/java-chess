package chess.domain.board;

import chess.domain.Position;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public void move(final Position source, final Position target) {
        Piece piece = board.get(source);
        if (!piece.canMove(source, target, getBoard())) {
            throw new IllegalArgumentException("이동이 불가능한 위치입니다.");
        }

        board.put(target, piece);
        board.put(source, new Empty());
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
