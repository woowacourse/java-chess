package model.piece.state;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.direction.Direction;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public abstract class Role {
    protected Color color;
    private final List<Direction> directions;

    protected Role(Color color, List<Direction> directionList) {
        this.color = color;
        this.directions = new ArrayList<>(directionList);
    }

    public void checkSameColor(Color color) {
        if (this.color == color) {
            throw new IllegalArgumentException("목표 지점에 같은 색깔의 말이 존재합니다.");
        }
    }

    public Route findDirectRoute(Position source, Position destination) {
        return possibleRoutes(source).stream()
                .filter(route -> route.contains(destination))
                .map(route -> route.subRoute(destination))
                .findAny()
                .orElseThrow(() ->  new IllegalArgumentException("해당 기물이 이동할 수 없는 좌표입니다"));
    }

    private Set<Route> possibleRoutes(Position position) {
        Set<Route> possibleRoutes = new HashSet<>();
        for (Direction direction : directions) {
            possibleRoutes.add(findMovingPatternRoute(direction, position));
        }
        return possibleRoutes;
    }

    protected abstract Route findMovingPatternRoute(Direction direction, Position movedPosition);

    public boolean isOccupied() {
        return true;
    }

    public Color getColor() {
        return color;
    }
}
