package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;

public class Bishop extends Piece {
    private static final int SCORE = 0;

    public Bishop(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean canMove(Position position) {
        return this.position.canMovePositiveDiagonally(position)
                || this.position.canMoveNegativeDiagonally(position);
    }

    @Override
    public String toString() {
        return "BISHOP";
    }
}
