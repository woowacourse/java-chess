package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "b";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int distanceX = Math.abs(source.calculateDistanceX(target));
        int distanceY = Math.abs(source.calculateDistanceY(target));

        if (distanceY >= 1 && distanceX == distanceY) {
            return true;
        }
        return false;
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();

        int routeLength = source.calculateDistanceFrom(target);
        int xSlope = source.calculateXSlope(target, routeLength);
        int ySlope = source.calculateYSlope(target, routeLength);

        for (int step = 1; step < routeLength; step++) {
            Position routeNode = source.shift(xSlope * step, ySlope * step);
            route.add(routeNode);
        }
        return route;
    }

    @Override
    public double score() {
        return 3;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
