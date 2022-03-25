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

        Position currentRoute = from.toNextPosition(direction);
        while (!currentRoute.equals(to)) {
            routes.add(currentRoute);
            currentRoute = currentRoute.toNextPosition(direction);
        }
        return routes;
    }

    public final boolean isBlack() {
        return color.isBlack();
    }

    public final boolean isSameColor(final ChessPiece chessPiece) {
        return this.color.equals(chessPiece.color);
    }

    public final boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    public final boolean isEnemyTurn(final Color color) {
        return !this.color.equals(color);
    }

    public final String getName() {
        return name;
    }

    public final double getValue() {
        return value;
    }
}
