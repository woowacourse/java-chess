package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.square.Square;
import chess.domain.square.Team;

public class KingDeadEnd implements State {
    private final Team winnerTeam;

    public KingDeadEnd(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    @Override
    public State start() {
        throw new IllegalStateException("킹이 죽은 상황에서는 재시작할 수 없습니다.");
    }

    @Override
    public State move(Square source, Square target) {
        throw new IllegalStateException("킹이 죽은 상황에서는 움직일 수 없습니다.");
    }

    @Override
    public State end() {
        return this;
    }

    @Override
    public double calculateScore(Team team) {
        throw new IllegalStateException("킹이 죽은 상황에서는 점수를 계산할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isKingDead() {
        return true;
    }

    @Override
    public Team getWinner() {
        return winnerTeam;
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException("킹이 죽은 상황에서는 보드를 가져올 수 없습니다.");
    }
}
