package chess.domain.gamestatus;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;

public abstract class Started implements GameStatus {

    protected Board board;

    protected Started() {
        this.board = BoardFactory.createInitially();
    }

    protected Started(Board board) {
        this.board = board;
    }
}
