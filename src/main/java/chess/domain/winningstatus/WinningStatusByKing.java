package chess.domain.winningstatus;

import chess.domain.piece.Team;

import java.util.Map;

public final class WinningStatusByKing implements WinningStatus {

    private final Team winner;

    public WinningStatusByKing(final Team winner) {
        this.winner = winner;
    }

    @Override
    public boolean isWinnerDetermined() {
        return true;
    }

    @Override
    public Map<Team, Score> getScores() {
        throw new IllegalStateException("게임이 종료되어 점수를 확인할 수 없습니다.");
    }

    @Override
    public Team getWinner() {
        return winner;
    }
}
