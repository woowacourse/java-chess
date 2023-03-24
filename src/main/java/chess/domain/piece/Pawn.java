package chess.domain.piece;

import chess.domain.square.Color;
import chess.domain.square.Team;

import java.util.List;

import static chess.domain.piece.Direction.*;

public class Pawn extends Piece {
    protected static final Direction WHITE_FORWARD_DIRECTION = NORTH;
    protected static final Direction BLACK_FORWARD_DIRECTION = SOUTH;
    protected static final List<Direction> WHITE_ATTACK_DIRECTION = List.of(NORTH_EAST, NORTH_WEST);
    protected static final List<Direction> BLACK_ATTACK_DIRECTION = List.of(SOUTH_EAST, SOUTH_WEST);
    private static final int MAX_MOVE_DISTANCE = 1;

    public Pawn(final Team team, final Role role) {
        super(team, role);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        if (distance > MAX_MOVE_DISTANCE) {
            return false;
        }
        if (this.team == Team.from(Color.WHITE)) {
            return WHITE_FORWARD_DIRECTION == direction;
        }
        return BLACK_FORWARD_DIRECTION == direction;
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final Piece targetPiece) {
        if (isSameSide(targetPiece.team)) {
            return false;
        }
        if (this.team == Team.from(Color.WHITE)) {
            return WHITE_ATTACK_DIRECTION.contains(direction) && distance == MAX_MOVE_DISTANCE;
        }
        return BLACK_ATTACK_DIRECTION.contains(direction) && distance == MAX_MOVE_DISTANCE;
    }
}
