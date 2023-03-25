package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.square.Color;
import chess.domain.square.Square;
import chess.domain.square.Team;

public class WaitingStart implements State {
    @Override
    public State start() {
        return new Running();
    }

    @Override
    public State move(final Square source, final Square target) {
        throw new IllegalStateException("보드 초기화 이전에 말을 움직일 수 없습니다.");
    }

    @Override
    public State end() {
        return new End(Team.from(Color.EMPTY));
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException("보드 초기화 이전에 보드를 가져올 수 없습니다.");
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public double calculateScore(final Team team) {
        throw new IllegalStateException("보드 초기화 이전에 점수를 계산할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Team getWinner() {
        throw new IllegalStateException("보드 초기화 이전에 우승자를 찾을 수 없습니다.");
    }
}
