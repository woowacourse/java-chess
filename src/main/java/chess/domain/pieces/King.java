package chess.domain.pieces;

import chess.domain.Team;

public class King implements Piece {

    private final Team team;

    public King(final Team team) {
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
