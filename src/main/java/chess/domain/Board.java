package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.square.Square;

import java.util.Map;

public class Board {

    private final Map<Square, Piece> board;

    private Board(final Map<Square, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        BoardMaker boardMaker = new BoardMaker();
        return new Board(boardMaker.make());
    }

    public void move(Square current, Square destination) {
        if (isEmptySquare(current)) {
            throw new IllegalArgumentException("해당 칸에 말이 존재하지 않습니다.");
        }
        if (canNotMove(current, destination)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        Piece piece = board.get(current);
        piece.canMove(current, destination);
        board.remove(current);
        board.put(destination, piece);
    }

    private boolean canNotMove(Square current, Square destination) {
        return !board.get(current).canMove(current, destination);
    }

    private boolean isEmptySquare(Square square) {
        return !board.containsKey(square);
    }
}
