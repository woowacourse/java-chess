package chess.piece;

import chess.Position;
import chess.Route;
import chess.Team;

public class Knight implements LMove {

    private final Team team;

    public Knight(final Team team) {
        this.team = team;
    }

    public Route moveUpRightUp(Position position) {
        Route route = new Route();
        if (position.canMoveUpRightUp()) {
            position = position.moveUp();
            route.add(position);
            position = position.moveRight();
            route.add(position);
            position = position.moveUp();
            route.add(position);
            return route;
        }
        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }


    public Route moveUpLeftUp(Position position) {
        Route route = new Route();
        if (position.canMoveUpLeftUp()) {
            position = position.moveUp();
            route.add(position);
            position = position.moveLeft();
            route.add(position);
            position = position.moveUp();
            route.add(position);
            return route;
        }
        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public Route moveRightUpRight(Position position) {
        Route route = new Route();
        if (position.canMoveRightUpRight()) {
            position = position.moveRight();
            route.add(position);
            position = position.moveUp();
            route.add(position);
            position = position.moveRight();
            route.add(position);
            return route;
        }
        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public Route moveRightDownRight(Position position) {
        Route route = new Route();
        if (position.canMoveRightDownRight()) {
            position = position.moveRight();
            route.add(position);
            position = position.moveDown();
            route.add(position);
            position = position.moveRight();
            route.add(position);
            return route;
        }
        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public Route moveDownRightDown(Position position) {
        Route route = new Route();
        if (position.canMoveDownRightDown()) {
            position = position.moveDown();
            route.add(position);
            position = position.moveRight();
            route.add(position);
            position = position.moveDown();
            route.add(position);
            return route;
        }
        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public Route moveDownLeftDown(Position position) {
        Route route = new Route();
        if (position.canMoveDownLeftDown()) {
            position = position.moveDown();
            route.add(position);
            position = position.moveLeft();
            route.add(position);
            position = position.moveDown();
            route.add(position);
            return route;
        }
        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public Route moveLeftUpLeft(Position position) {
        Route route = new Route();
        if (position.canMoveLeftUpLeft()) {
            position = position.moveLeft();
            route.add(position);
            position = position.moveUp();
            route.add(position);
            position = position.moveLeft();
            route.add(position);
            return route;
        }
        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public Route moveLeftDownLeft(Position position) {
        Route route = new Route();
        if (position.canMoveLeftDownLeft()) {
            position = position.moveLeft();
            route.add(position);
            position = position.moveDown();
            route.add(position);
            position = position.moveLeft();
            route.add(position);
            return route;
        }
        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    @Override
    public PieceMoveType getMoveType() {
        return PieceMoveType.KNIGHT;
    }

}
