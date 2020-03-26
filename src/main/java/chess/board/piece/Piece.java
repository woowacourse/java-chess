package chess.board.piece;

import chess.board.MoveInfo;
import chess.board.Vector;

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
        return this instanceof Blank;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean isSameTeam(Piece piece) {
        return this.team == piece.team;
    }

    public abstract boolean canMove(MoveInfo moveInfo, Piece targetPiece);

    public double getScore() {
        return score;
    }
}
