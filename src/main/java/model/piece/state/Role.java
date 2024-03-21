package model.piece.state;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import model.position.Position;
import model.position.Route;
import model.direction.MovingPattern;
import model.piece.Color;

public abstract class Role {
    protected Color color;
    private List<MovingPattern> movingPatterns;

    protected Role(Color color, List<MovingPattern> movingPatternList) {
        this.color = color;
        this.movingPatterns = new ArrayList<>(movingPatternList);
    }

    public void checkSameCamp(Role role) {
        if (this.color == role.color){
            throw new IllegalArgumentException("같은 진영의 기물이 목적 지점에 위치합니다.");
        }
    }

    public boolean isOccupied(){
        return true;
    }

    protected abstract Set<Route> possibleRoutes(Position position);

    public Route findRoute(Position source, Position destination) {
        Set<Route> possibleRoutes = possibleRoutes(source);
        for (Route route : possibleRoutes) {
            if (route.contains(destination)) {
                return route.subRoute(destination);
            }
        }
        throw new IllegalArgumentException(" ");
    }

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
