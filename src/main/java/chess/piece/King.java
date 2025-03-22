package chess.piece;

import chess.Position;
import chess.Route;
import chess.Team;

public class King implements DiagonalMove, PerpendicularMove {

    private final Team team;

    public King(final Team team) {
        this.team = team;
    }

    @Override
    public Route moveRightUp(Position position) {
        Route route = new Route();
        if (position.canMoveRightUp()) {
            position = position.moveRightUp();
            route.add(position);
            return route;
        }
        return null;
    }

    @Override
    public Route moveRightDown(Position position) {
        Route route = new Route();
        if (position.canMoveRightDown()) {
            position = position.moveRightDown();
            route.add(position);
            return route;
        }
        return null;
    }

    @Override
    public Route moveLeftUp(Position position) {
        Route route = new Route();
        if (position.canMoveLeftUp()) {
            position = position.moveLeftUp();
            route.add(position);
            return route;
        }
        return null;
    }

    @Override
    public Route moveLeftDown(Position position) {
        Route route = new Route();
        if (position.canMoveLeftDown()) {
            position = position.moveLeftDown();
            route.add(position);
            return route;
        }
        return null;
    }

    @Override
    public PieceMoveType getMoveType() {
        return PieceMoveType.KING;
    }

    @Override
    public Route moveUp(Position position) {
        Route route = new Route();
        if (position.canMoveUp()) {
            position = position.moveUp();
            route.add(position);
            return route;
        }
        return null;
    }

    @Override
    public Route moveDown(Position position) {
        Route route = new Route();
        if (position.canMoveDown()) {
            position = position.moveDown();
            route.add(position);
            return route;
        }
        return null;
    }

    @Override
    public Route moveRight(Position position) {
        Route route = new Route();
        if (position.canMoveRight()) {
            position = position.moveRight();
            route.add(position);
            return route;
        }
        return null;
    }

    @Override
    public Route moveLeft(Position position) {
        Route route = new Route();
        if (position.canMoveLeft()) {
            position = position.moveLeft();
            route.add(position);
            return route;
        }
        return null;
    }
}
