package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "r";
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int distanceX = Math.abs(source.calculateDistanceX(target));
        int distanceY = Math.abs(source.calculateDistanceY(target));

        if (distanceY >= 1 && distanceX == 0) {
            return true;
        }
        if (distanceY == 0 && distanceX >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();
        int sourcePositionX = source.getPositionX().getCoordination();
        int sourcePositionY = source.getPositionY().getCoordination();

        int routeLength = Math.max(Math.abs(source.calculateDistanceY(target)), Math.abs(source.calculateDistanceX(target)));
        int xSlope = source.calculateDistanceX(target) / routeLength;
        int ySlope = source.calculateDistanceY(target) / routeLength;

        for (int step = 1; step < routeLength; step++) {
            route.add(new Position(PositionX.of(sourcePositionX + xSlope * step), PositionY.of(sourcePositionY + ySlope * step)));
        }
        return route;
    }
}
