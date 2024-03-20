package chess.domain.piece;

import chess.domain.Point;

public abstract class Piece {

    private final String name;
    private final Team team;

    public Piece(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public abstract boolean isMovable(Point currentPoint, Point nextPoint);

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public String getName() {
        if (team.isWhite()) {
            return name.toLowerCase();
        }
        return name;
    }

    public Team getTeam() {
        return team;
    }
}
