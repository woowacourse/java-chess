package chess.domain.pieces;

import chess.domain.Team;

public class EmptyPiece implements Piece {

    private final Team team;

    public EmptyPiece() {
        this.team = Team.WHITE;
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
