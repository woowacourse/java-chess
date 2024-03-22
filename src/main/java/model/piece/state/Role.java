package model.piece.state;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.direction.MovingPattern;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public abstract class Role {
    protected Color color;
    private final List<MovingPattern> movingPatterns;

    protected Role(Color color, List<MovingPattern> movingPatternList) {
        this.color = color;
        this.movingPatterns = new ArrayList<>(movingPatternList);
    }

    public void checkSameColor(Color color) {
        if (this.color == color) {
            throw new IllegalArgumentException("목표 지점에 같은 색깔의 말이 존재합니다.");
        }
    }

    public boolean isOccupied() {
        return true;
    }

    public Route findDirectRoute(Position source, Position destination) {
        return possibleRoutes(source).stream()
                .filter(route -> route.contains(destination))
                .map(route -> route.subRoute(destination))
                .findAny()
                .orElseThrow(() ->  new IllegalArgumentException("해당 기물이 이동할 수 없는 좌표입니다"));
    }

    public Set<Route> possibleRoutes(Position position) {
        Set<Route> possibleRoutes = new HashSet<>();
        for (MovingPattern movingPattern : movingPatterns) {
            possibleRoutes.add(findMovingPatternRoute(movingPattern, position));
        }
        return possibleRoutes;
    }

    protected Route findMovingPatternRoute(MovingPattern movingPattern, Position movedPosition) {
        List<Position> sequentialPositions = new ArrayList<>();
        while (movedPosition.isAvailablePosition(movingPattern)) {
            movedPosition = movedPosition.getNextPosition(movingPattern);
            sequentialPositions.add(movedPosition);
        }
        return new Route(sequentialPositions);
    }

    public Color getColor() {
        return color;
    }
}
