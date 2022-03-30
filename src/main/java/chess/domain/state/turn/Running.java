package chess.domain.state.turn;

import chess.domain.piece.Piece;
import chess.domain.Team;

public abstract class Running implements State {

    private static final String WRONG_SOURCE_ERROR = "상대 팀의 기물을 옮길 수 없습니다.";
    private static final String WRONG_TARGET_ERROR = "같은 팀의 기물로 이동할 수 없습니다.";

    private final Team team;

    protected Running(Team team) {
        this.team = team;
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final State play(final Piece target) {
        if (target.isKing()) {
            return new KingDeath(team);
        }
        return next();
    }

    @Override
    public final Team getTeam() {
        return team;
    }

    protected abstract State next();
}
