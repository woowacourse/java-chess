package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.position.Position;

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
    public State move(Position source, Position target) {
        board.move(source, target);
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
