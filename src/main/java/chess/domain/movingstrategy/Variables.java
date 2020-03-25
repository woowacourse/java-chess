package chess.domain.movingstrategy;

import chess.domain.Team;
import chess.domain.position.Position;

public class Variables {
    private Position fromPosition;
    private Position toPosition;
    private Team team;

    public Variables(Position fromPosition, Position toPosition, Team team) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
        this.team = team;
    }

    public Position getFromPosition() {
        return fromPosition;
    }

    public Position getToPosition() {
        return toPosition;
    }

    public Team getTeam() {
        return team;
    }
}
