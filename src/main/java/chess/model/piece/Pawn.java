package chess.model.piece;

import chess.model.board.Coordinate;
import chess.model.board.vector.Direction;
import chess.model.board.Route;
import chess.model.board.vector.Vector;
import chess.model.routeCreator.CreatingRouteStrategy;
import chess.model.routeCreator.PawnCreatingRouteStrategy;

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

        if (!("white".equals(team) && vector.isMatch(movableDirectionsForWhiteTeam))
                && !("black".equals(team) && vector.isMatch(movableDirectionsForBlackTeam))) {
            throw new IllegalArgumentException("이 방향으로 움직일 수 없습니다.");
        }

        CreatingRouteStrategy strategy = new PawnCreatingRouteStrategy(isNotMoved);
        return strategy.create(sourceCoordinates, vector);
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
