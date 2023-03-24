package chess.domain.game;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public final class Turn {

    private final Team team;

    private Turn(final Team team) {
        this.team = team;
    }

    public static Turn create() {
        return new Turn(Team.WHITE);
    }

    private static Turn of(final Team team) {
        return new Turn(team);
    }

    public Turn next() {
        return of(team.getOpponentTeam());
    }

    public Team getTeam() {
        return team;
    }

    public boolean isValidTurn(final Piece piece) {
        return getTeam() == piece.getTeam();
    }
}
