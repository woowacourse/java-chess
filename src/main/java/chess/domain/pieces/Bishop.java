package chess.domain.pieces;

import chess.domain.position.Position;
import chess.domain.Team;

public class Bishop extends Piece {
    private static final double SCORE = 3;

    public Bishop(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean canMove(Position position) {
        return this.position.canMovePositiveDiagonally(position)
                || this.position.canMoveNegativeDiagonally(position);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String getSymbol() {
        return "b";
    }

    @Override
    public String toString() {
        return "BISHOP";
    }
}
