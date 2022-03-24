package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public String signature() {
        return color.correctSignature(baseSignature());
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    protected abstract String baseSignature();

    protected List<Position> findLinearRoute(Position source, Position target){
        List<Position> route = new ArrayList<>();

        int routeLength = source.calculateDisplacementFrom(target);
        int xSlope = source.calculateXSlope(target, routeLength);
        int ySlope = source.calculateYSlope(target, routeLength);

        for (int step = 1; step < routeLength; step++) {
            Position routeNode = source.displacement(xSlope * step, ySlope * step);
            route.add(routeNode);
        }
        return route;
    }

    public abstract boolean isMovable(Position source, Position target);

    public abstract List<Position> findRoute(Position source, Position target);

    public abstract double score();

    public abstract boolean isBlank();

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public abstract boolean isRook();
}
