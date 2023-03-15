package chess.domain.pieces;

import chess.domain.Team;

public class Knight implements Piece {

    private final Team team;

    public Knight(final Team team) {
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
