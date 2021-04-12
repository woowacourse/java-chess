package domain.state;

import domain.Board;
import domain.score.Score;
import domain.score.ScoreMachine;

public abstract class Started implements State {
    protected Board board;
    protected boolean turn;

    public Started(Board board) {
        this.board = board;
    }

    public Started(Board board, boolean turn) {
        this(board);
        this.turn = turn;
    }

    @Override
    public Score blackScore() {
        return ScoreMachine.blackPiecesScore(board);
    }

    @Override
    public Score whiteScore() {
        return ScoreMachine.whitePiecesScore(board);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean getTurn() {
        return turn;
    }
}
