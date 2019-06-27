package chess.model.piece;

import chess.model.board.Coordinate;
import chess.model.board.vector.Direction;
import chess.model.board.Route;
import chess.model.board.vector.Vector;

import java.util.*;

import static chess.model.board.vector.Direction.*;

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
        validateTeam(team);

        this.isNotMoved = isNotMoved;
        this.team = team;
    }

    private void validateTeam(String team) {
        if (Objects.isNull(team) || team.isEmpty()) {
            throw new NullPointerException();
        }
        if (!"white".equals(team) && !"black".equals(team)) {
            throw new IllegalArgumentException("없는 팀입니다!");
        }
    }

    @Override
    public Route produceRoute(List<Coordinate> sourceCoordinates, Vector vector) {
        validateNull(sourceCoordinates, vector);

        List<String> route = new ArrayList<>();
        if ("white".equals(team)) {
            return createRouteWhenWhite(sourceCoordinates, vector, route);
        }

        if ("black".equals(team)) {
            return createRouteWhenBlack(sourceCoordinates, vector, route);
        }

        return new Route(route);
    }

    private Route createRouteWhenWhite(List<Coordinate> sourceCoordinates, Vector vector, List<String> route) {
        if (vector.isMatch(movableDirectionsForWhiteTeam)) {
            addWhenMagnitude2(sourceCoordinates, vector, route);
            addWhenMagnitude1(sourceCoordinates, vector, route);

            return new Route(route);
        }
        throw new IllegalArgumentException("백팀 폰은 이 방향으로 움직일 수 없습니다");
    }

    private Route createRouteWhenBlack(List<Coordinate> sourceCoordinates, Vector vector, List<String> route) {
        if (vector.isMatch(movableDirectionsForBlackTeam)) {
            addWhenMagnitude2(sourceCoordinates, vector, route);
            addWhenMagnitude1(sourceCoordinates, vector, route);

            return new Route(route);
        }
        throw new IllegalArgumentException("흑팀 폰은 이 방향으로 움직일 수 없습니다");
    }

    private void validateNull(List<Coordinate> coordinates, Vector vector) {
        if (Objects.isNull(coordinates) || coordinates.isEmpty()) {
            throw new NullPointerException();
        }
        if (Objects.isNull(vector)) {
            throw new NullPointerException();
        }
    }

    private void addWhenMagnitude2(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.isEqualToMagnitude(2) && isNotMoved) {
            addWhenNorthGivenMagnitude2(coordinates, vector, route);
            addWhenSouthGivenMagnitude2(coordinates, vector, route);
        }

        if (vector.isEqualToMagnitude(2) && !isNotMoved) {
            throw new IllegalArgumentException("한 턴에 2칸을 이동할 수 없습니다.");
        }
    }


    private void addWhenNorthGivenMagnitude2(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.isEqualToDirection(NORTH)) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);

            route.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(1)));
            route.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(2)));
        }
    }

    private void addWhenSouthGivenMagnitude2(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.isEqualToDirection(SOUTH)) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);

            route.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(-1)));
            route.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(-2)));
        }
    }

    private void addWhenMagnitude1(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.isEqualToMagnitude(1)) {
            addWhenSouthWest(coordinates, vector, route);
            addWhenSouthEast(coordinates, vector, route);
            addWhenNorthEast(coordinates, vector, route);
            addWhenNorthWest(coordinates, vector, route);
            addWhenNorthGivenMagnitude1(coordinates, vector, route);
            addWhenSouthGivenMagnitude1(coordinates, vector, route);
        }
    }

    private void addWhenSouthWest(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.isEqualToDirection(SOUTHWEST)) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.addCoordinate(-1).concat(coordinateY.addCoordinate(-1)));
        }
    }

    private void addWhenSouthEast(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.isEqualToDirection(SOUTHEAST)) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.addCoordinate(1).concat(coordinateY.addCoordinate(-1)));
        }
    }

    private void addWhenNorthEast(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.isEqualToDirection(NORTHEAST)) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.addCoordinate(1).concat(coordinateY.addCoordinate(1)));
        }
    }

    private void addWhenNorthWest(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.isEqualToDirection(NORTHWEST)) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.addCoordinate(-1).concat(coordinateY.addCoordinate(1)));
        }
    }

    private void addWhenNorthGivenMagnitude1(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.isEqualToDirection(NORTH)) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(1)));
        }
    }

    private void addWhenSouthGivenMagnitude1(List<Coordinate> coordinates, Vector vector, List<String> route) {
        if (vector.isEqualToDirection(SOUTH)) {
            Coordinate coordinateX = coordinates.get(0);
            Coordinate coordinateY = coordinates.get(1);
            route.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(-1)));
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
    public boolean isKing() {
        return false;
    }

    @Override
    public String askTeamColor() {
        if ("white".equals(team)) {
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
