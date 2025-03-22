package chess.piece;

import chess.Position;
import chess.Route;
import chess.Team;

public class Rook implements PerpendicularMove {

    private final Team team;

    public Rook(final Team team) {
        this.team = team;
    }

    public Route moveUp(Position position) {
        Route route = new Route();
        while (!position.isTop()) {
            position = position.moveUp();
            route.add(position);
        }
        return route;
    }

    public Route moveDown(Position position) {
        Route route = new Route();
        while (!position.isBottom()) {
            position = position.moveDown();
            route.add(position);
        }
        return route;
    }

    public Route moveRight(Position position) {
        Route route = new Route();
        while (!position.isFarRight()) {
            position = position.moveRight();
            route.add(position);
        }
        return route;
    }

    public Route moveLeft(Position position) {
        Route route = new Route();
        while (!position.isFarLeft()) {
            position = position.moveLeft();
            route.add(position);
        }
        return route;
    }

    @Override
    public PieceMoveType getMoveType() {
        return PieceMoveType.ROOK;
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
