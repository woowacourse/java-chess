package chess.domain.gamestatus;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.score.Score;

public abstract class Started implements GameStatus {

    protected Board board;

    protected Started() {
        this.board = BoardFactory.createInitially();
    }

    protected Started(Board board) {
        this.board = board;
    }

    @Override
    public String getBoardString() {
        return board.toString();
    }

    @Override
    public Score scoring() {
        return board.calculateScore();
    }
}
