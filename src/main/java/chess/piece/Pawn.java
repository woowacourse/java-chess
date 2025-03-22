package chess.piece;

import chess.Position;
import chess.Route;
import chess.Team;

public class Pawn implements PerpendicularMove {

    private final Team team;

    public Pawn(final Team team) {
        this.team = team;
    }

    @Override
    public Route moveUp(final Position position) {
        if (team == Team.BLACK) {
            return null;
        }
        return Route.from(position.moveUp());
    }

    @Override
    public Route moveDown(final Position position) {
        if (team == Team.WHITE) {
            return null;
        }
        return Route.from(position.moveDown());
    }

    @Override
    public Route moveRight(final Position position) {
        return null;

    }

    @Override
    public Route moveLeft(final Position position) {
        return null;
    }

    public Route moveUpUp(final Position position) {
        Route route = moveUp(position);
        route.add(route.getLast().moveUp());
        return route;
    }

    public Route moveDownDown(final Position position) {
        Route route = moveDown(position);
        route.add(route.getLast().moveDown());
        return route;
    }

    @Override
    public PieceMoveType getMoveType() {
        return PieceMoveType.PAWN;
    }
}
