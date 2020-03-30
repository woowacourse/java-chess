package domain.pieces;

import static domain.team.Team.NONE;

import domain.pieces.exceptions.IsNotMovableException;
import domain.point.Column;
import domain.point.Point;
import domain.point.MovePoint;
import domain.team.Team;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pieces {

    private static final String INITIAL_PAWN = "p";
    private static final int ZERO = 0;
    private static final int PIECES_PAWN_MINIMUM_COUNT = 1;
    private static final double SCORE_HALF_PAWN = 0.5;

    private Map<Point, Piece> pieces;

    private Pieces(Map<Point, Piece> pieces) {
        this.pieces = new LinkedHashMap<>(pieces);
    }

    public static Pieces of(Map<Point, Piece> pieces) {
        return new Pieces(pieces);
    }

    public Map<Point, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    public Piece getPiece(Point point) {
        return pieces.get(point);
    }

    public boolean isExistPiece(Point point) {
        return getPiece(point).team != NONE;
    }

    public void move(Team turn, MovePoint movePoint) {
        validateMovable(turn, movePoint);
        pieces.put(movePoint.getTo(), pieces.get(movePoint.getFrom()));
        pieces.put(movePoint.getFrom(), new Empty(NONE));
    }

    private void validateMovable(Team turn, MovePoint movePoint) {
        validateExistPiece(movePoint);
        validateCorrectTurn(turn, movePoint);
        validateSameTeamToTarget(movePoint);
        validatePieceMovable(turn, movePoint);
    }

    private void validateExistPiece(MovePoint movePoint) {
        if (!isExistPiece(movePoint.getFrom())) {
            throw new IsNotMovableException(movePoint.getFrom() + "에 움직일 수 있는 말이 없습니다.");
        }
    }

    private void validateCorrectTurn(Team turn, MovePoint movePoint) {
        if (!getPiece(movePoint.getFrom()).isSameTeam(turn)) {
            throw new IsNotMovableException(turn.toString() + "차례입니다.");
        }
    }

    private void validateSameTeamToTarget(MovePoint movePoint) {
        if (isSameTeamToTarget(movePoint)) {
            throw new IsNotMovableException("움직이려는 곳에 같은 색 말이 있습니다.");
        }
    }

    private boolean isSameTeamToTarget(MovePoint movePoint) {
        return movePoint.isSameTeam(pieces);
    }

    private void validatePieceMovable(Team team, MovePoint movePoint) {
        if (!isMovable(team, movePoint)) {
            throw new IsNotMovableException("그 장소로 못 움직입니다.");
        }
    }

    private boolean isMovable(Team team, MovePoint movePoint) {
        return pieces.get(movePoint.getFrom()).getDirection(team).stream()
            .anyMatch(direction -> pieces.get(movePoint.getFrom()).isMovable(direction, pieces, movePoint));
    }

    public double score(Team team) {
        double totalScore = pieces.keySet().stream()
            .filter(point -> pieces.get(point).isSameTeam(team))
            .mapToDouble(this::getPieceScore)
            .reduce(0, Double::sum);

        int pawnCount = getSameColumnPawnCount(team);
        return totalScore - pawnCount * SCORE_HALF_PAWN;
    }

    private double getPieceScore(Point point) {
        return pieces.get(point).getScore();
    }

    private int getSameColumnPawnCount(Team team) {
        int pawnCount = 0;
        int count;
        for (Column column : Column.values()) {
            count = (int) pieces.keySet().stream()
                .filter(point -> pieces.get(point).isSameTeam(team))
                .filter(point -> isSameColumn(point, column))
                .filter(this::isSameInitial)
                .count();

            pawnCount += addCountMoreThanTWo(count);
        }
        return pawnCount;
    }

    private int addCountMoreThanTWo(int count) {
        if (count > PIECES_PAWN_MINIMUM_COUNT) {
            return count;
        }
        return ZERO;
    }

    private boolean isSameColumn(Point point, Column column) {
        return point.isSameColumn(column);
    }

    private boolean isSameInitial(Point point) {
        return pieces.get(point).getInitial().equalsIgnoreCase(INITIAL_PAWN);
    }

    public boolean isTargetKing() {
        int countKing = (int)pieces.keySet().stream()
            .filter(point -> pieces.get(point).isKing())
            .count();

        return countKing < 2;
    }
}