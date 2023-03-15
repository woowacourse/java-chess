package chess.domain.pieces;

import chess.domain.Team;

public class Bishop implements Piece {

    private final Team team;

    public Bishop(final Team team) {
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
