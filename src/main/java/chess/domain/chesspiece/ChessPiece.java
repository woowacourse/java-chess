package chess.domain.chesspiece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.Optional;
import java.util.Stack;

public abstract class ChessPiece {

    protected static final String INVALID_TARGET_POSITION = "해당 기물이 갈 수 없는 위치입니다.";
    protected static final String TARGET_SAME_COLOR_MESSAGE = "같은색 기물입니다.";

    protected final Color color;
    private final String name;

    protected ChessPiece(final Color color, final String name) {
        this.color = color;
        this.name = color.convertByColor(name);
    }

    public abstract void checkMovablePosition(final Position from, final Position to,
                                              final Optional<ChessPiece> possiblePiece);

    protected void checkTargetPosition(final Optional<ChessPiece> possiblePiece) {
        if (possiblePiece.isEmpty()) {
            return;
        }

        final ChessPiece targetPiece = possiblePiece.get();
        if (targetPiece.isSameColor(color)) {
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

    public final boolean isSameColor(final ChessPiece chessPiece) {
        return color.equals(chessPiece.color);
    }

    public final boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    public boolean isKing() {
        return false;
    }

    public final String name() {
        return name;
    }

    public abstract double value();
}
