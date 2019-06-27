package chess.model.piece;

import chess.model.board.Coordinate;
import chess.model.board.vector.Direction;
import chess.model.board.Route;
import chess.model.board.vector.Vector;
import chess.model.routeCreator.RouteCreator;

import java.util.*;

import static chess.model.board.vector.Direction.*;
import static chess.model.board.Board.*;


public class Pawn implements Piece {
    public static final double SCORE = 1.0;
    private static final Set<Direction> movableDirectionsForWhiteTeam;
    private static final Set<Direction> movableDirectionsForBlackTeam;

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
        if (!WHITE_TEAM.equals(team) && !BLACK_TEAM.equals(team)) {
            throw new IllegalArgumentException("없는 팀입니다!");
        }
    }

    @Override
    public Route produceRoute(List<Coordinate> sourceCoordinates, Vector vector) {
        validateNull(sourceCoordinates, vector);

        if (!(WHITE_TEAM.equals(team) && vector.isMatch(movableDirectionsForWhiteTeam))
                && !(BLACK_TEAM.equals(team) && vector.isMatch(movableDirectionsForBlackTeam))) {
            throw new IllegalArgumentException("이 방향으로 움직일 수 없습니다.");
        }

        return RouteCreator.createByPawn(sourceCoordinates, vector, isNotMoved);
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
        if (WHITE_TEAM.equals(team)) {
            return WHITE_TEAM;
        }
        return BLACK_TEAM;
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
