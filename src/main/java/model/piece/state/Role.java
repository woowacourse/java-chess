package model.piece.state;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.direction.Direction;
import model.direction.ShiftPattern;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public abstract class Role {
    protected Color color;
    private final ShiftPattern shiftPattern;

    protected Role(Color color, ShiftPattern shiftPattern) {
        this.color = color;
        this.shiftPattern = shiftPattern;
    }

    public void checkSameColor(Color color) {
        if (this.color == color) {
            throw new IllegalArgumentException("목표 지점에 같은 색깔의 말이 존재합니다.");
        }
    }

    public Route findDirectRoute(Position source, Position destination) {
        return findAllPossibleRoutes(source).stream()
                .filter(route -> route.contains(destination))
                .map(route -> route.subRoute(destination))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 기물이 이동할 수 없는 좌표입니다"));
    }

    private Set<Route> findAllPossibleRoutes(Position source) {
        Set<Route> possibleRoutes = new HashSet<>();
        List<Direction> directions = shiftPattern.directions();
        for (Direction direction : directions) {
            possibleRoutes.add(findRouteByDirection(direction, source));
        }
        return possibleRoutes;
    }

    protected abstract Route findRouteByDirection(Direction direction, Position source);

    public boolean isOccupied() {
        return true;
    }

    public Color getColor() {
        return color;
    }
}
