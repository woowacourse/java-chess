package chess.domain.pieces;

import chess.domain.Team;

public class Pawn implements Piece {

    private final Team team;

    public Pawn(final Team team) {
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
