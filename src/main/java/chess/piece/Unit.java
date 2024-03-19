package chess.piece;

import chess.Position;
import chess.Team;

public abstract class Unit {
    protected Position position;
    protected Team team;

    public Unit(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    public abstract void move(Position position);
}
