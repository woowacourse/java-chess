package chess.domain.pieces;

import chess.domain.Team;

public class Queen implements Piece {

    private final Team team;

    public Queen(final Team team) {
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
