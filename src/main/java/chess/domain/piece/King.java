package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.TeamColor;
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
        super(teamColor, PieceType.KING);
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
    public boolean canMove(List<Piece> piecesOnRoute, Position start, Position target) {
        return piecesOnRoute.getLast().isEmpty() || this.isOtherTeam(piecesOnRoute.getLast());
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
