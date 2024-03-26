package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.point.Point;

public class End implements State {

    @Override
    public State start() {
        throw new UnsupportedOperationException("이미 게임이 종료되었습니다.");
    }

    @Override
    public State finish() {
        throw new UnsupportedOperationException("이미 게임이 종료되었습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State move(final Board board, final Point departure, final Point destination) {
        throw new UnsupportedOperationException("이미 게임이 종료되었습니다.");
    }
}
