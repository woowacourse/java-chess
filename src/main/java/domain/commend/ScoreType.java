package domain.commend;

import domain.commend.exceptions.isNotExistPieceInitialException;
import domain.pieces.Pieces;
import domain.point.Column;
import domain.point.Point;
import domain.team.Team;
import java.util.Arrays;

public enum ScoreType {
    KING("k", 0),
    QUEEN("q", 9),
    ROOK("r", 5),
    BISHOP("b", 3),
    KNIGHT("n", 2.5),
    PAWN("p", 1),
    NONE(".", 0);

    private static final String INITIAL_PAWN = "p";
    private static final int ZERO = 0;
    private static final int PIECES_PAWN_MINIMUM_COUNT = 1;
    private static final double SCORE_HALF_PAWN = 0.5;

    private String initial;
    private double score;

    ScoreType(String initial, double score) {
        this.initial = initial;
        this.score = score;
    }

    public static double calculateBlackScore(Pieces pieces, Team team) {
        double totalScore = pieces.getPieces().keySet().stream()
            .filter(point -> pieces.getPieces().get(point).isSameTeam(team))
            .mapToDouble(point -> getPieceScore(pieces, point))
            .reduce(0, Double::sum);

        int pawnCount = getSameColumnPawnCount(pieces, team);
        return totalScore - pawnCount * SCORE_HALF_PAWN;
    }

    private static int getSameColumnPawnCount(Pieces pieces, Team team) {
        int pawnCount = 0;
        int count;
        for (Column column : Column.values()) {
            count = (int) pieces.getPieces().keySet().stream()
                .filter(point -> isSameTeam(pieces, team, point))
                .filter(point -> isSameColumn(point, column))
                .filter(point -> isSameInitial(pieces, point))
                .count();

            pawnCount += addCountMoreThanTWo(count);
        }
        return pawnCount;
    }

    private static int addCountMoreThanTWo(int count) {
        if (count > PIECES_PAWN_MINIMUM_COUNT) {
            return count;
        }
        return ZERO;
    }

    private static boolean isSameTeam(Pieces pieces, Team team, Point point) {
        return pieces.getPiece(point).isSameTeam(team);
    }

    private static boolean isSameColumn(Point point, Column column) {
        return point.isSameColumn(column);
    }

    private static boolean isSameInitial(Pieces pieces, Point point) {
        return pieces.getPiece(point).getInitial().equalsIgnoreCase(INITIAL_PAWN);
    }

    private static double getPieceScore(Pieces pieces, Point point) {
        return Arrays.stream(ScoreType.values())
            .filter(x -> pieces.getPieces().get(point).getInitial().equalsIgnoreCase(x.initial))
            .findFirst()
            .orElseThrow(() -> new isNotExistPieceInitialException("존재하지 않는 말입니다."))
            .score;
    }
}
