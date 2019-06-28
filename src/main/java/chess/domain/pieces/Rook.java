package chess.domain.pieces;

import chess.domain.position.Position;
import chess.domain.Team;

public class Rook extends Piece {
    private static final double SCORE = 5;

    public Rook(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean canMove(Position position) {
        return this.position.canMoveBackAndForth(position)
                || this.position.canMoveSideToSide(position);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String getSymbol() {
        return "r";
    }

    @Override
    public String toString() {
        return "ROOK";
    }
}
