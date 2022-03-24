package chess.domain.piece;

import chess.domain.state.Direction;
import java.util.List;
import java.util.Map;

public abstract class Piece {
    private final Team team;
    private final Name name;

    public Piece(Team team, Name name) {
        this.team = team;
        this.name = name;
    }

    public String getName() {
        return name.getName(team);
    }

    @Override
    public String toString() {
        return getName();
    }

    public boolean isBlack() {
        return team.isBlack();
    }

    public boolean isWhite() {
        return team.isWhite();
    }

    public abstract boolean isMovableDirection(Direction direction);
}
