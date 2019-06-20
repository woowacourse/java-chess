package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;

public class Pawn extends Piece {
    private static final int SCORE = 0;
    private static final int DISTANCE_FOR_ALL_DIRECTION = 1;
    private static final int DISTANCE_FOR_STARTING_POSITION = 4;
    private static final int STANDARD_DIRECTION_VALUE = 0;

    public Pawn(Position position, Team team) {
        super(position, team);
    }

    public boolean canMove(Position position) {
        return isPossibleDistance(position) && isPossibleDirection(position);
    }

    public boolean isPossibleDistance(Position position) {
        return (this.position.getDistanceSquare(position) == DISTANCE_FOR_ALL_DIRECTION)
                || (this.position.isInStartingPosition() && this.position.getDistanceSquare(position) == DISTANCE_FOR_STARTING_POSITION);
    }

    public boolean isPossibleDirection(Position position) {
        return team.equals(Team.WHITE) && this.position.subtractRow(position) < STANDARD_DIRECTION_VALUE
                || team.equals(Team.BLACK) && this.position.subtractRow(position) > STANDARD_DIRECTION_VALUE;
    }
}
