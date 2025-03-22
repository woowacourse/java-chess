package chess.piece;

import chess.Position;
import chess.Route;
import chess.Team;

public class Bishop implements DiagonalMove {

    private final Team team;

    public Bishop(final Team team) {
        this.team = team;
    }

    public Route moveRightUp(Position position) {
        Route route = new Route();
        while (!(position.isTop() || position.isFarRight())) {
            position = position.moveRightUp();
            route.add(position);
        }
        return route;
    }

    public Route moveRightDown(Position position) {
        Route route = new Route();
        while (!(position.isBottom() || position.isFarRight())) {
            position = position.moveRightDown();
            route.add(position);
        }
        return route;
    }

    public Route moveLeftUp(Position position) {
        Route route = new Route();
        while (!(position.isTop() || position.isFarLeft())) {
            position = position.moveLeftUp();
            route.add(position);
        }
        return route;
    }

    public Route moveLeftDown(Position position) {
        Route route = new Route();
        while (!(position.isBottom() || position.isFarLeft())) {
            position = position.moveLeftDown();
            route.add(position);
        }
        return route;
    }

    @Override
    public PieceMoveType getMoveType() {
        return PieceMoveType.BISHOP;
    }
}
