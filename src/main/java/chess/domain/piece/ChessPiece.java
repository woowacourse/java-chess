package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;
import java.util.Stack;

public abstract class ChessPiece {

    private final Color color;
    private final String name;
    private final double value;

    protected ChessPiece(Color color, String name, double value) {
        this.color = color;
        this.name = color.convertByColor(name);
        this.value = value;
    }

    public abstract List<Position> getInitWhitePosition();

    public abstract List<Position> getInitBlackPosition();

    public abstract void canMove(Position from, Position to);

    public boolean isBlack() {
        return color.isBlack();
    }

    public String getName() {
        return name;
    }

    public Stack<Position> findRoute(final Position from, Position to) {
        Stack<Position> routes = new Stack<>();
        Direction direction = to.findDirection(from);

        Position newFrom = new Position(from.getValue());

        while (!newFrom.equals(to)) {
            Position nextPosition = newFrom.toNextPosition(direction);
            routes.add(new Position(nextPosition.getValue()));
            newFrom = nextPosition;
        }

        routes.pop();

        return routes;
    }

    public boolean isSameColor(ChessPiece chessPiece) {
        return this.color.equals(chessPiece.color);
    }

    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    public boolean isEnemyTurn(Color color) {
        return !this.color.equals(color);
    }

    public double getValue() {
        return value;
    }

}
