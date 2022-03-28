package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public abstract class Piece {

    private final Team team;
    protected Position position;
    private final double score;

    public Piece(Team team, Position position, double score) {
        this.team = team;
        this.position = position;
        this.score = score;
    }

    public Team getTeam() {
        return team;
    }

    public double getScore() {
        return score;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }

    public abstract List<Position> findPath(Position destination);

    public boolean isBlank() {
        return false;
    };

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public void move(Position destination) {
        position = destination;
    }
}
