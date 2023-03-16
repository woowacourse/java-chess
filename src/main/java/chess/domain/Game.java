package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import java.util.List;

public class Game {
    private final Board board;

    public Game() {
        this.board = new Board();
    }

    public List<Piece> getPieces() {
        return board.getPieces();
    }

    public void move(final Square source, final Square target) {
        if (!board.move(source, target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }
}
