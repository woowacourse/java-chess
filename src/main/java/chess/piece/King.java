package chess.piece;

import chess.Movement;
import chess.Position;
import chess.TeamColor;
import java.util.List;

public class King extends Piece{
    private final static List<Movement> kingMovement =
            List.of(Movement.UP,
                    Movement.DOWN,
                    Movement.LEFT,
                    Movement.RIGHT,
                    Movement.LEFT_UP,
                    Movement.RIGHT_UP,
                    Movement.LEFT_DOWN,
                    Movement.RIGHT_DOWN);

    private final List<Movement> availableMovement;

    public King(TeamColor teamColor) {
        super(teamColor);
        this.availableMovement = kingMovement;
    }

    public boolean availablePath(Position start, Position target) {
        for (Movement movement : availableMovement) {
            if(!start.canMove(movement)) {
                continue;
            }

            Position moved = start.move(movement);
            if(moved.equals(target)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Position> findAllRouteToTarget(Position start, Position target) {
        return List.of(target);
    }

    @Override
    public boolean canMove(Piece targetPiece, Position start, Position target) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
