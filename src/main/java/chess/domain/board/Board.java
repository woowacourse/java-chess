package chess.domain.board;

import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class Board {

    private final Map<Square, Piece> board;

    public Board(final Map<Square, Piece> board) {
        this.board = board;
    }

    public Optional<Piece> findPieceOf(final Square square) {
        return Optional.ofNullable(board.get(square));
    }

    public void move(final Square source, final Square destination) {
        board.put(destination, board.remove(source));
    }

    public BoardSnapShot getBoardSnapShot() {
        return BoardSnapShot.from(board);
    }

    public Map<Square, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
