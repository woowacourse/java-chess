package chess.domain;

import java.util.List;

public abstract class Piece {

    private Team team;
    private final String name;
    protected Position position;

    public Piece(Team team, String name, Position position) {
        this.team = team;
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }

    public abstract List<Position> findPath(Position destination);
}
