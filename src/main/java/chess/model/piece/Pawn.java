package chess.model.piece;

import chess.model.Coordinate;
import chess.model.Direction;
import chess.model.Route;
import chess.model.Vector;

import java.util.*;

public class Pawn implements Piece {
    private static final Set<Direction> movableDirectionsForWhiteTeam;
    private static final Set<Direction> movableDirectionsForBlackTeam;

    static {
        movableDirectionsForWhiteTeam = new HashSet<>();
        movableDirectionsForWhiteTeam.add(Direction.NORTH);
        movableDirectionsForWhiteTeam.add(Direction.NORTHEAST);
        movableDirectionsForWhiteTeam.add(Direction.NORTHWEST);

        movableDirectionsForBlackTeam = new HashSet<>();
        movableDirectionsForBlackTeam.add(Direction.SOUTH);
        movableDirectionsForBlackTeam.add(Direction.SOUTHEAST);
        movableDirectionsForBlackTeam.add(Direction.SOUTHWEST);
    }

    private boolean isNotMoved;
    private String team;

    public Pawn(boolean isNotMoved, String team) {
        validateInput(team);

        this.isNotMoved = isNotMoved;
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
    public Pawn cloneSelf() {
        return new Pawn(false, team);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public Route produceRoute(List<Coordinate> coordinates, Vector vector) {
        validateInput(coordinates, vector);

        List<String> route = new ArrayList<>();
        if ("white".equals(team)) {
            if (vector.isMatch(movableDirectionsForWhiteTeam)) {
                addWhenMagnitude2(coordinates, vector, route);
                addWhenMagnitude1(coordinates, vector, route);

                return new Route(route);
            }
            throw new IllegalArgumentException("백팀 폰은 이 방향으로 움직일 수 없습니다");
        }

        if ("black".equals(team)) {
            if (vector.isMatch(movableDirectionsForBlackTeam)) {
                addWhenMagnitude2(coordinates, vector, route);
                addWhenMagnitude1(coordinates, vector, route);

                return new Route(route);
            }
            throw new IllegalArgumentException("흑팀 폰은 이 방향으로 움직일 수 없습니다");
        }

        return new Route(route);
    }

    private void validateInput(List<Coordinate> coordinates, Vector vector) {
        if (Objects.isNull(coordinates) || coordinates.isEmpty()) {
            throw new NullPointerException();
        }
        if (Objects.isNull(vector)) {
            throw new NullPointerException();
        }
    }

    private void addWhenMagnitude2(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getMagnitude().getMagnitude() == 2 && isNotMoved) {
            addWhenNorthGivenMagnitude2(coordinates, vector, route);
            addWhenSouthGivenMagnitude2(coordinates, vector, route);
        }
    }


    private void addWhenNorthGivenMagnitude2(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == Direction.NORTH) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);

            route.add(coordinateX.convertToString(0).concat(coordinateY.convertToString(1)));
            route.add(coordinateX.convertToString(0).concat(coordinateY.convertToString(2)));
        }
    }

    private void addWhenSouthGivenMagnitude2(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == Direction.SOUTH) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);

            route.add(coordinateX.convertToString(0).concat(coordinateY.convertToString(-1)));
            route.add(coordinateX.convertToString(0).concat(coordinateY.convertToString(-2)));
        }
    }

    private void addWhenMagnitude1(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getMagnitude().getMagnitude() == 1) {
            addWhenSouthWest(coordinates, vector, route);
            addWhenSouthEast(coordinates, vector, route);
            addWhenNorthEast(coordinates, vector, route);
            addWhenNorthWest(coordinates, vector, route);
            addWhenNorthGivenMagnitude1(coordinates, vector, route);
            addWhenSouthGivenMagnitude1(coordinates, vector, route);
        }
    }

    private void addWhenSouthWest(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == Direction.SOUTHWEST) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.convertToString(-1).concat(coordinateY.convertToString(-1)));
        }
    }

    private void addWhenSouthEast(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == Direction.SOUTHEAST) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.convertToString(1).concat(coordinateY.convertToString(-1)));
        }
    }

    private void addWhenNorthEast(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == Direction.NORTHEAST) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.convertToString(1).concat(coordinateY.convertToString(1)));
        }
    }

    private void addWhenNorthWest(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == Direction.NORTHWEST) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.convertToString(-1).concat(coordinateY.convertToString(1)));
        }
    }

    private void addWhenNorthGivenMagnitude1(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == Direction.NORTH) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.convertToString(0).concat(coordinateY.convertToString(1)));
        }
    }

    private void addWhenSouthGivenMagnitude1(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == Direction.SOUTH) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.convertToString(0).concat(coordinateY.convertToString(-1)));
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pawn pawn = (Pawn) o;
        return isNotMoved == pawn.isNotMoved &&
                Objects.equals(team, pawn.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isNotMoved, team);
    }
}
