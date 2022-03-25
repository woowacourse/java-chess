package chess.domain.chessPiece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Stack;

public abstract class ChessPiece {

    private final Color color;
    private final String name;
    private final double value;

    protected ChessPiece(final Color color, final String name, final double value) {
        this.color = color;
        this.name = color.convertByColor(name);
        this.value = value;
    }

    public abstract List<Position> getInitWhitePosition();

    public abstract List<Position> getInitBlackPosition();

    public abstract void canMove(final Position from, final Position to);

    public Stack<Position> findRoute(final Position from, final Position to) {
        final Stack<Position> routes = new Stack<>();
        final Direction direction = to.findDirection(from);

        Position newFrom = new Position(from.getValue());

        while (!newFrom.equals(to)) {
            final Position nextPosition = newFrom.toNextPosition(direction);
            routes.add(new Position(nextPosition.getValue()));
            newFrom = nextPosition;
        }

        routes.pop();

        return routes;
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isSameColor(final ChessPiece chessPiece) {
        return this.color == chessPiece.color;
    }

    public boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    public boolean isEnemyTurn(final Color color) {
        return this.color != color;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
}
