package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.dto.StateType;

import java.util.function.ObjDoubleConsumer;

public class End extends State {

    public End(final Board board) {
        this.board = board;
        this.stateType = StateType.END;
    }

    @Override
    public State start() {
        throw new IllegalStateException("게임이 종료됐습니다.");
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new IllegalStateException("게임이 종료됐습니다.");
    }

    @Override
    public State end() {
        throw new IllegalStateException("게임이 이미 종료됐습니다.");
    }

    @Override
    public void status(final ObjDoubleConsumer<String> printScore) {
        throw new IllegalStateException("게임이 종료됐습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isEnded() {
        return true;
    }
}
