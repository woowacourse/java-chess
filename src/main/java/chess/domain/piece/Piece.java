package chess.domain.piece;

import chess.domain.player.TeamType;

public abstract class Piece {

    private final TeamType teamType;
    private final String name;

    public Piece(TeamType teamType, String name) {
        this.teamType = teamType;
        this.name = name;
    }

    public String getName() {
        if (teamType == TeamType.WHITE) {
            return name.toLowerCase();
        }
        return name;
    }

    public boolean isTeamOf(TeamType teamType) {
        return this.teamType == teamType;
    }
}
