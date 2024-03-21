package model.piece.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import model.direction.MovingPattern;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public abstract class Role {
    protected Color color;
    private List<MovingPattern> movingPatterns;

    protected Role(Color color, List<MovingPattern> movingPatternList) {
        this.color = color;
        this.movingPatterns = new ArrayList<>(movingPatternList);
    }

    public void checkSameCamp(Role role) {
        if (this.color == role.color) {
            throw new IllegalArgumentException("같은 진영의 기물이 목적 지점에 위치합니다.");
        }
    }

    public boolean isOccupied() {
        return true;
    }

    public Route findRoute(Position source, Position destination) {
        return possibleRoutes(source).stream()
                .filter(route -> route.contains(destination))
                .map(route -> route.subRoute(destination))
                .findAny()
                .orElseThrow(() ->  new IllegalArgumentException("해당 기물이 이동할 수 없는 좌표입니다"));
    }

    protected abstract Set<Route> possibleRoutes(Position position);

    protected Route getRoute(MovingPattern movingPattern, Position movedPosition) {
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
