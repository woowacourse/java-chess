package chess.model.piece;

import chess.model.board.Coordinate;
import chess.model.board.vector.Direction;
import chess.model.board.Route;
import chess.model.board.vector.Vector;
import chess.model.routeCreator.RouteCreator;

import java.util.*;

import static chess.model.board.vector.Direction.*;

public class Rook implements Piece {
    private static final Set<Direction> movableDirections;
    public static final double SCORE = 5.0;

    static {
        movableDirections = new HashSet<>();
        movableDirections.add(NORTH);
        movableDirections.add(SOUTH);
        movableDirections.add(EAST);
        movableDirections.add(WEST);
    }

    private String team;

    public Rook(String team) {
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
        if (!vector.isMatch(movableDirections)) {
            throw new IllegalArgumentException("이 방향으로 이동할 수 없습니다.");
        }

        return RouteCreator.createByNormalPiece(sourceCoordinates, vector);
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
        return new Rook(team);
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
        Rook rook = (Rook) o;
        return Objects.equals(team, rook.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
