package chess.piece;

import chess.coordinate.Direction;
import chess.coordinate.Vector;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected final Team team;
    private final double score;

    public Piece(final Team team, final double score) {
        this.team = team;
        this.score = score;
    }

    public boolean isBlack() {
        return team.equals(Team.BLACK);
    }

    public List<Direction> findPath(Vector vector) {
        List<Direction> path = new ArrayList<>();
        for (int i = 0; i < vector.getMaxValue(); i++) {
            path.add(vector.getUnitVector());
        }
        return path;
    }

    public boolean isBlank() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean canMove(Vector vector, Piece targetPiece) {
        if (targetPiece.isSameTeam(this.team)) {
            return false;
        }

        return canReach(vector, targetPiece);
    }

    protected abstract boolean canReach(Vector vector, Piece targetPiece);

    public double getScore() {
        return score;
    }

    public Piece move() {
        return this;
    }
}
