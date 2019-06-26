package chess.domain.pieces;

import chess.domain.position.Position;
import chess.domain.Team;

public class Knight extends Piece {
    private static final double SCORE = 2.5;
    private static final int DISTANCE_FOR_ALL_DIRECTION = 5;

    public Knight(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean canMove(Position position) {
        return this.position.getDistanceSquare(position) == DISTANCE_FOR_ALL_DIRECTION;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String getSymbol() {
        return "n";
    }

    @Override
    public String toString() {
        return "KNIGHT";
    }
}
