package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected final Color color;
    protected int moveCount;

    public Piece(Color color) {
        this.color = color;
        this.moveCount = 0;
    }

    public String signature() {
        return color.correctSignature(baseSignature());
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isNeverDisplaced() {
        return moveCount == 0;
    }

    public Piece displaced() {
        this.moveCount++;
        return this;
    }

    protected List<Position> findLinearRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();

        int routeLength = source.calculateMaxLinearLengthTo(target);
        int xChangeRatio = source.calculateXSlope(target, routeLength);
        int yChangeRatio = source.calculateYSlope(target, routeLength);

        for (int step = 1; step < routeLength; step++) {
            Position routeNode = source.displacedOf(xChangeRatio * step, yChangeRatio * step);
            route.add(routeNode);
        }
        return route;
    }

    public abstract boolean isEnPassantAvailable();

    protected abstract String baseSignature();

    public abstract boolean isMovable(Position source, Position target);

    public abstract List<Position> findRoute(Position source, Position target);

    public abstract double score();

    public abstract boolean isBlank();

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public abstract boolean isRook();
}
