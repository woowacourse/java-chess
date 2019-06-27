package chess.model.piece;

import chess.model.board.Coordinate;
import chess.model.board.vector.Direction;
import chess.model.board.Route;
import chess.model.board.vector.Vector;

import java.util.*;

import static chess.model.board.vector.Direction.*;

// TODO: 2019-06-22 cloneSelf를 꼭 써야할까?
public class Queen implements Piece {
    private static final Set<Direction> movableDirections;
    public static final double SCORE = 9.0;

    static {
        movableDirections = new HashSet<>();
        movableDirections.add(NORTH);
        movableDirections.add(NORTHEAST);
        movableDirections.add(NORTHWEST);
        movableDirections.add(SOUTH);
        movableDirections.add(SOUTHWEST);
        movableDirections.add(SOUTHEAST);
        movableDirections.add(EAST);
        movableDirections.add(WEST);
    }

    private String team;

    public Queen(String team) {
        //todo produceRoute 로직 funtional interface로 빼기
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

            if (vector.isEqualToDirection(NORTH)) {
                for (int i = 1; i <= vector.getMagnitude().getMagnitude(); i++) {
                    routes.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(i)));
                }
            }
            if (vector.isEqualToDirection(SOUTH)) {
                for (int i = 1; i <= vector.getMagnitude().getMagnitude(); i++) {
                    routes.add(coordinateX.addCoordinate(0).concat(coordinateY.addCoordinate(-i)));
                }
            }
            if (vector.isEqualToDirection(WEST)) {
                for (int i = 1; i <= vector.getMagnitude().getMagnitude(); i++) {
                    routes.add(coordinateX.addCoordinate(-i).concat(coordinateY.addCoordinate(0)));
                }
            }
            if (vector.isEqualToDirection(EAST)) {
                for (int i = 1; i <= vector.getMagnitude().getMagnitude(); i++) {
                    routes.add(coordinateX.addCoordinate(i).concat(coordinateY.addCoordinate(0)));
                }
            }
            if (vector.isEqualToDirection(SOUTHEAST)) {
                for (int i = 1; i <= vector.getMagnitude().getMagnitude(); i++) {
                    routes.add(coordinateX.addCoordinate(i).concat(coordinateY.addCoordinate(-i)));
                }
            }
            if (vector.isEqualToDirection(SOUTHWEST)) {
                for (int i = 1; i <= vector.getMagnitude().getMagnitude(); i++) {
                    routes.add(coordinateX.addCoordinate(-i).concat(coordinateY.addCoordinate(-i)));
                }
            }
            if (vector.isEqualToDirection(NORTHEAST)) {
                for (int i = 1; i <= vector.getMagnitude().getMagnitude(); i++) {
                    routes.add(coordinateX.addCoordinate(i).concat(coordinateY.addCoordinate(i)));
                }
            }
            if (vector.isEqualToDirection(NORTHWEST)) {
                for (int i = 1; i <= vector.getMagnitude().getMagnitude(); i++) {
                    routes.add(coordinateX.addCoordinate(-i).concat(coordinateY.addCoordinate(i)));
                }
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
        return new Queen(team);
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
        Queen queen = (Queen) o;
        return Objects.equals(team, queen.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
