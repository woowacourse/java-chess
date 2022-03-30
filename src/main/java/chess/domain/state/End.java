package chess.domain.state;

import chess.domain.Board;
import chess.domain.Score;
import chess.domain.postion.Position;

import java.util.List;

public final class End implements State {

    private final Board board;

    public End(final Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException("End 상태에서는 start할 수 없습니다.");
    }

    @Override
    public State end() {
        throw new IllegalArgumentException("게임이 종료되었습니다.");
    }

    @Override
    public State changeTurn(final List<Position> positions) {
        throw new IllegalArgumentException("End 상태에서는 chageTurn할 수 없습니다.");
    }

    @Override
    public Score status() {
        return Score.from(board());
    }

    @Override
    public Board board() {
        return board;
    }
}
