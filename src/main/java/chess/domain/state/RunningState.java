package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.Turn;
import chess.domain.board.Board;

public class RunningState implements State {

    private Board board;

    public RunningState(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("이미 게임이 시작되었습니다.");
    }

    @Override
    public State move(MoveParameter moveParameter, Turn turn) {
        board.move(moveParameter.getSource(), moveParameter.getTarget(), turn);
        return this;
    }

    @Override
    public State end() {
        return new EndState(board);
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
