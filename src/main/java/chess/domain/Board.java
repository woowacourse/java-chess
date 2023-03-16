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
}
