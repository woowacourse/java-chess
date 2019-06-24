package chess.model.piece;

import chess.model.Coordinate;
import chess.model.Direction;
import chess.model.Route;
import chess.model.Vector;

import java.util.*;

import static chess.model.Direction.*;

public class Pawn implements Piece {
    private static final Set<Direction> movableDirectionsForWhiteTeam;
    private static final Set<Direction> movableDirectionsForBlackTeam;
    public static final double SCORE = 1.0;

    static {
        movableDirectionsForWhiteTeam = new HashSet<>();
        movableDirectionsForWhiteTeam.add(NORTH);
        movableDirectionsForWhiteTeam.add(NORTHEAST);
        movableDirectionsForWhiteTeam.add(NORTHWEST);

        movableDirectionsForBlackTeam = new HashSet<>();
        movableDirectionsForBlackTeam.add(SOUTH);
        movableDirectionsForBlackTeam.add(SOUTHEAST);
        movableDirectionsForBlackTeam.add(SOUTHWEST);
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
    public double getScore() {
        return SCORE;
    }

    @Override
    public Route produceRoute(List<Coordinate> sourceCoordinates, Vector vector) {
        validateInput(sourceCoordinates, vector);

        List<String> route = new ArrayList<>();
        if ("white".equals(team)) {
            if (vector.isMatch(movableDirectionsForWhiteTeam)) {
                addWhenMagnitude2(sourceCoordinates, vector, route);
                addWhenMagnitude1(sourceCoordinates, vector, route);

                return new Route(route);
            }
            throw new IllegalArgumentException("백팀 폰은 이 방향으로 움직일 수 없습니다");
        }

        if ("black".equals(team)) {
            if (vector.isMatch(movableDirectionsForBlackTeam)) {
                addWhenMagnitude2(sourceCoordinates, vector, route);
                addWhenMagnitude1(sourceCoordinates, vector, route);

                return new Route(route);
            }
            throw new IllegalArgumentException("흑팀 폰은 이 방향으로 움직일 수 없습니다");
        }

//        throw new IllegalArgumentException("이 방향으로 이동할 수 없습니다.");
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

        if (vector.getMagnitude().getMagnitude() == 2 && !isNotMoved) {
            throw new IllegalArgumentException("한 턴에 2칸을 이동할 수 없습니다.");
        }
    }


    private void addWhenNorthGivenMagnitude2(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == NORTH) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);

            route.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(1)));
            route.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(2)));
        }
    }

    private void addWhenSouthGivenMagnitude2(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == SOUTH) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);

            route.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(-1)));
            route.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(-2)));
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
        if (vector.getDirection() == SOUTHWEST) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.addCoordinate(-1).concat(coordinateY.addCoordinate(-1)));
        }
    }

    private void addWhenSouthEast(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == SOUTHEAST) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.addCoordinate(1).concat(coordinateY.addCoordinate(-1)));
        }
    }

    private void addWhenNorthEast(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == NORTHEAST) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.addCoordinate(1).concat(coordinateY.addCoordinate(1)));
        }
    }

    private void addWhenNorthWest(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == NORTHWEST) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.addCoordinate(-1).concat(coordinateY.addCoordinate(1)));
        }
    }

    private void addWhenNorthGivenMagnitude1(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == NORTH) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(1)));
        }
    }

    private void addWhenSouthGivenMagnitude1(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.getDirection() == SOUTH) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(-1)));
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
