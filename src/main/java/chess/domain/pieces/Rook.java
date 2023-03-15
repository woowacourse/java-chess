package chess.domain.pieces;

import chess.domain.Team;

public class Rook implements Piece {

    private final Team team;

    public Rook(final Team team) {
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
