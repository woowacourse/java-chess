package chess.model.piece;

import chess.model.board.Coordinate;
import chess.model.board.vector.Direction;
import chess.model.board.Route;
import chess.model.board.vector.Vector;
import chess.model.routeCreator.RouteCreator;

import java.util.*;

import static chess.model.board.vector.Direction.*;
import static chess.model.board.Board.*;


// TODO: 2019-06-22 cloneSelf를 꼭 써야할까?
public class Queen implements Piece {
    public static final double SCORE = 9.0;
    private static final Set<Direction> movableDirections;

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
        validateTeam(team);
        this.team = team;
    }

    private void validateTeam(String team) {
        if (Objects.isNull(team) || team.isEmpty()) {
            throw new NullPointerException();
        }
        if (!WHITE_TEAM.equals(team) && !BLACK_TEAM.equals(team)) {
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
        if (WHITE_TEAM.equals(team)) {
            return WHITE_TEAM;
        }
        return BLACK_TEAM;
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
