package chess.model.piece;

import chess.model.Coordinate;
import chess.model.Direction;
import chess.model.Route;
import chess.model.Vector;

import java.util.*;

import static chess.model.Direction.*;
import static chess.model.Direction.NORTHWEST;

public class Bishop implements Piece {
    private static Set<Direction> movableDirections;

    static {
        movableDirections = new HashSet<>();
        movableDirections.add(SOUTHEAST);
        movableDirections.add(SOUTHWEST);
        movableDirections.add(NORTHWEST);
        movableDirections.add(NORTHEAST);
    }

    private String team;

    public Bishop(String team) {
        validateInput(team);
        this.team = team;
    }

    private void validateInput(String team) {
        if (Objects.isNull(team)) {
            throw new NullPointerException();
        }
        if (!team.equals("white") && !team.equals("black")) {
            throw new IllegalArgumentException("없는 팀입니다!");
        }
    }

    @Override
    public Route produceRoute(List<Coordinate> sourceCoordinates, Vector vector) {
        validateInput(sourceCoordinates, vector);

        List<String> routes = new ArrayList<>();

        if (vector.isMatch(movableDirections)) {
            Coordinate coordinateX = sourceCoordinates.get(0);
            Coordinate coordinateY = sourceCoordinates.get(1);

            if (vector.getDirection() == SOUTHEAST) {
                for (int i = 1; i <= vector.getMagnitude().getMagnitude(); i++) {
                    routes.add(coordinateX.addCoordinate(i).concat(coordinateY.addCoordinate(-i)));
                }
            }
            if (vector.getDirection() == SOUTHWEST) {
                for (int i = 1; i <= vector.getMagnitude().getMagnitude(); i++) {
                    routes.add(coordinateX.addCoordinate(-i).concat(coordinateY.addCoordinate(-i)));
                }
            }
            if (vector.getDirection() == NORTHEAST) {
                for (int i = 1; i <= vector.getMagnitude().getMagnitude(); i++) {
                    routes.add(coordinateX.addCoordinate(i).concat(coordinateY.addCoordinate(i)));
                }
            }
            if (vector.getDirection() == NORTHWEST) {
                for (int i = 1; i <= vector.getMagnitude().getMagnitude(); i++) {
                    routes.add(coordinateX.addCoordinate(-i).concat(coordinateY.addCoordinate(i)));
                }
            }

            return new Route(routes);
        }

        throw new IllegalArgumentException("이 방향으로 이동할 수 없습니다.");
    }

    private void validateInput(List<Coordinate> coordinates, Vector vector) {
        if (Objects.isNull(coordinates) || coordinates.isEmpty()) {
            throw new NullPointerException();
        }

        if (Objects.isNull(vector)) {
            throw new NullPointerException();
        }
    }

    @Override
    public String askTeamColor() {
        if (this.team.equals("white")) {
            return "white";
        }
        return "black";
    }

    @Override
    public Piece cloneSelf() {
        return new Bishop(team);
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
