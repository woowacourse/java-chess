package chess.domain.chesspiece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.Stack;

public abstract class ChessPiece {

    protected static final String CHECK_POSITION_ERROR_MESSAGE = "해당 기물이 갈 수 없는 위치입니다.";

    protected final Color color;
    private final String name;

    protected ChessPiece(final Color color, final String name) {
        this.color = color;
        this.name = color.convertByColor(name);
    }

    public abstract void checkMovablePosition(final Position from, final Position to);

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

    public final boolean isSameColor(final ChessPiece chessPiece) {
        return color.equals(chessPiece.color);
    }

    public final boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    public final String name() {
        return name;
    }

    public abstract double value();
}
