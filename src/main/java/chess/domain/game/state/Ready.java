package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.point.Point;

public class Ready implements State {
    private final Team team;

    public Ready(final Team team) {
        this.team = team;
    }

    @Override
    public State start() {
        return new Running(team);
    }

    @Override
    public State finish() {
        return new End();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State move(final Board board, final Point departure, final Point destination) {
        throw new UnsupportedOperationException("아직 게임이 시작하지 않아 실행할 수 없습니다.");
    }
}
