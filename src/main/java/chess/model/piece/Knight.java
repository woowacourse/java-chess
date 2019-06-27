package chess.model.piece;

import chess.model.board.Coordinate;
import chess.model.board.vector.Direction;
import chess.model.board.Route;
import chess.model.board.vector.Vector;

import java.util.*;

import static chess.model.board.vector.Direction.*;

public class Knight implements Piece {
    private static Set<Direction> movableDirections;
    public static final double SCORE = 2.5;

    static {
        movableDirections = new HashSet<>();
        movableDirections.add(KNIGHT_EASTNORTH);
        movableDirections.add(KNIGHT_EASTSOUTH);
        movableDirections.add(KNIGHT_NORTHEAST);
        movableDirections.add(KNIGHT_NORTHWEST);
        movableDirections.add(KNIGHT_SOUTHEAST);
        movableDirections.add(KNIGHT_SOUTHWEST);
        movableDirections.add(KNIGHT_WESTNORTH);
        movableDirections.add(KNIGHT_WESTSOUTH);
    }

    private String team;

    public Knight(String team) {
        validateTeam(team);
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

        List<String> routes = new ArrayList<>();

        if (vector.isMatch(movableDirections)) {
            Coordinate coordinateX = sourceCoordinates.get(0);
            Coordinate coordinateY = sourceCoordinates.get(1);

            if (vector.getDirection() == KNIGHT_EASTNORTH) {
                routes.add(coordinateX.addCoordinate(2).concat(coordinateY.addCoordinate(1)));
            }
            if (vector.getDirection() == KNIGHT_EASTSOUTH) {
                routes.add(coordinateX.addCoordinate(2).concat(coordinateY.addCoordinate(-1)));
            }
            if (vector.getDirection() == KNIGHT_NORTHEAST) {
                routes.add(coordinateX.addCoordinate(1).concat(coordinateY.addCoordinate(2)));
            }
            if (vector.getDirection() == KNIGHT_NORTHWEST) {
                routes.add(coordinateX.addCoordinate(-1).concat(coordinateY.addCoordinate(2)));
            }
            if (vector.getDirection() == KNIGHT_SOUTHEAST) {
                routes.add(coordinateX.addCoordinate(1).concat(coordinateY.addCoordinate(-2)));
            }
            if (vector.getDirection() == KNIGHT_SOUTHWEST) {
                routes.add(coordinateX.addCoordinate(-1).concat(coordinateY.addCoordinate(-2)));
            }
            if (vector.getDirection() == KNIGHT_WESTNORTH) {
                routes.add(coordinateX.addCoordinate(-2).concat(coordinateY.addCoordinate(1)));
            }
            if (vector.getDirection() == KNIGHT_WESTSOUTH) {
                routes.add(coordinateX.addCoordinate(-2).concat(coordinateY.addCoordinate(-1)));
            }

            return new Route(routes);
        }

        throw new IllegalArgumentException("이 방향으로 이동할 수 없습니다.");
    }

    private void validateNull(List<Coordinate> coordinates, Vector vector) {
        if (Objects.isNull(coordinates) || coordinates.isEmpty()) {
            throw new NullPointerException();
        }

        if (Objects.isNull(vector)) {
            throw new NullPointerException();
        }
    }

    @Override
    public String askTeamColor() {
        if ("white".equals(team)) {
            return "white";
        }
        return "black";
    }

    @Override
    public Piece cloneSelf() {
        return new Knight(team);
    }

    @Override
    public boolean isPawn() {
        return false;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knight knight = (Knight) o;
        return Objects.equals(team, knight.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
