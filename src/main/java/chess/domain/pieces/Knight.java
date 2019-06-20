package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;

public class Knight extends Piece {
    private static final int SCORE = 0;
    private static final int DISTANCE_FOR_ALL_DIRECTION = 5;

    public Knight(Position position, Team team) {
        super(position, team);
    }

    boolean canMove(Position position) {
        return this.position.getDistanceSquare(position) == DISTANCE_FOR_ALL_DIRECTION;
    }
}
