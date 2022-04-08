package chess.domain.state.turn;

import chess.domain.Team;
import chess.domain.piece.Piece;

public abstract class Running implements State {

    private final Team team;

    protected Running(Team team) {
        this.team = team;
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public final State play(final Piece target) {
        if (target.isKing() && team.isBlack()) {
            return new BlackWin();
        }
        if (target.isKing() && team.isWhite()) {
            return new WhiteWin();
        }
        return next();
    }

    @Override
    public final Team getTeam() {
        return team;
    }

    protected abstract State next();
}
