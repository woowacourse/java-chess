package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "p";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int displacementX = source.calculateDisplacementXTo(target);
        int displacementY = source.calculateDisplacementYTo(target);

        if (color == Color.BLACK) {
            return isBlackMovable(displacementX, displacementY);
        }
        return isWhiteMovable(displacementX, displacementY);
    }

    private boolean isBlackMovable(int displacementX, int displacementY) {
        if (displacementY == 1 && displacementX == 0) {
            return true;
        }
        if (displacementY == 1 && Math.abs(displacementX) == 1) {
            return true;
        }
        if (displacementY == 2 && displacementX == 0) {
            return isNeverDisplaced();
        }
        return false;
    }

    private boolean isWhiteMovable(int displacementX, int displacementY) {
        if (displacementY == -1 && displacementX == 0) {
            return true;
        }
        if (displacementY == -1 && Math.abs(displacementX) == 1) {
            return true;
        }
        if (displacementY == -2 && displacementX == 0) {
            return isNeverDisplaced();
        }
        return false;
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();

        int routeLength = source.calculateMaxLinearLengthTo(target);
        int xSlope = source.calculateXSlope(target, routeLength);
        int ySlope = source.calculateYSlope(target, routeLength);

        for (int step = 1; step < routeLength; step++) {
            Position routeNode = source.displacedOf(xSlope * step, ySlope * step);
            route.add(routeNode);
        }
        return route;
    }

    @Override
    public double score() {
        return 1;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}

