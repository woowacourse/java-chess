package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;

public class King extends Piece {
    private static final int SCORE = 0;
    private static final int DISTANCE_FOR_FOUR_DIRECTION = 1;
    private static final int DISTANCE_FOR_DIAGONAL_DIRECTION = 2;

    public King(Position position, Team team) {
        super(position, team);
    }

    @Override
    boolean canMove(Position position) {
        return this.position.getDistanceSquare(position) == DISTANCE_FOR_FOUR_DIRECTION
                || this.position.getDistanceSquare(position) == DISTANCE_FOR_DIAGONAL_DIRECTION;
    }
}
