package chess.domain.pieces;

import chess.domain.position.Position;
import chess.domain.Team;

public class Queen extends Piece {
    private static final double SCORE = 9;

    public Queen(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean canMove(Position position) {
        return this.position.canMoveBackAndForth(position)
                || this.position.canMoveSideToSide(position)
                || this.position.canMovePositiveDiagonally(position)
                || this.position.canMoveNegativeDiagonally(position);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String toString() {
        return "QUEEN";
    }
}
