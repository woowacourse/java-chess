package chess.board.piece;

import chess.board.Vector;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected final Team team;

    public Piece(final Team team) {
        this.team = team;
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

    public abstract boolean canMove(Vector vector);

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean isSameTeam(Piece piece) {
        return this.team == piece.team;
    }
}
