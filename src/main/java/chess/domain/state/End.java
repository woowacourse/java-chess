package chess.domain.state;

import chess.domain.Board;
import chess.domain.Score;
import chess.domain.postion.Position;

import java.util.List;

public final class End implements State {

    public End() {
    }

    @Override
    public State start() {
        throw new IllegalArgumentException("게임이 종료되었습니다.");
    }

    @Override
    public State end() {
        throw new IllegalArgumentException("게임이 종료되었습니다.");
    }

    @Override
    public boolean isGameOver() {
        return true;
    }

    @Override
    public State changeTurn(final List<Position> positions) {
        throw new IllegalArgumentException("End 상태에서는 chageTurn할 수 없습니다.");
    }

    @Override
    public Score status() {
        throw new IllegalArgumentException("End 상태에서는 status할 수 없습니다.");
    }

    @Override
    public Board board() {
        throw new IllegalArgumentException("End 상태에서는 board할 수 없습니다.");
    }
}
