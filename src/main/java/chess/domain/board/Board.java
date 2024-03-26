package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Map;

public class Board {

    private final BoardInitializer boardInitializer;
    private final Map<Position, Piece> board;

    public Board(final BoardInitializer boardInitializer) {
        this.boardInitializer = boardInitializer;
        this.board = boardInitializer.initialize();
    }

    public void tryMove(final Position source, final Position target) {
        Piece piece = board.get(source);
        if (piece.canMove(source, target, getBoard())) {
            move(source, target, piece);
            return;
        }

        throw new IllegalArgumentException("이동이 불가능한 위치입니다.");
    }

    private void move(final Position source, final Position target, final Piece piece) {
        board.put(target, piece);
        board.put(source, Piece.getEmptyPiece());
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
