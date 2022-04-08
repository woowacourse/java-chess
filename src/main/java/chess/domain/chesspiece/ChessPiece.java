package chess.domain.chesspiece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.Objects;
import java.util.Stack;

public abstract class ChessPiece {

    protected static final String INVALID_TARGET_POSITION = "해당 기물이 갈 수 없는 위치입니다.";
    protected static final String TARGET_SAME_COLOR_MESSAGE = "같은색 기물입니다.";

    protected final Color color;

    protected ChessPiece(final Color color) {
        this.color = color;
    }

    public abstract void checkMovablePosition(final Position from, final Position to, final ChessPiece chessPiece);

    protected void checkTargetPosition(final ChessPiece chessPiece) {
        if (Objects.isNull(chessPiece)) {
            return;
        }

        if (chessPiece.isSameColor(color)) {
            throw new IllegalArgumentException(TARGET_SAME_COLOR_MESSAGE);
        }
    }

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

    public final boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public abstract double value();

    public Color color() {
        return color;
    }

}
