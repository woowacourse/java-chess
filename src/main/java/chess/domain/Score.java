package chess.domain;

import chess.domain.position.Column;
import chess.domain.position.Row;
import java.util.EnumMap;
import java.util.Map;

public class Score {

    private static final int PAWN_HALF_SCORE_BOUND = 2;
    public static final double SAME_COLUMN_PAWN_SCORE = 0.5;

    private final double totalScore;

    private Score(double totalScore) {
        this.totalScore = totalScore;
    }

    public static Score calculateScore(Map<Row, Rank> board, Team team) {
        Map<Column, Integer> pawnNeighbors = new EnumMap<>(Column.class);
        double totalScore = 0;
        for (Row row : board.keySet()) {
            totalScore += board.get(row).calculateRankTotalScore(team);
            findNeighborPawns(board.get(row), team, pawnNeighbors);
        }
        return new Score(reCalculateWithPawnNeighbors(totalScore, pawnNeighbors));
    }

    private static void findNeighborPawns(Rank rank, Team team, Map<Column, Integer> pawnNeighbors) {
        for (Column column : Column.values()) {
            findNeighborsInColumn(rank, team, column, pawnNeighbors);
        }
    }

    private static void findNeighborsInColumn(Rank rank, Team team, Column column, Map<Column, Integer> pawnNeighbors) {
        if (rank.isSameTeamPawn(team, column)) {
            pawnNeighbors.put(column, pawnNeighbors.getOrDefault(column, 0) + 1);
        }
    }

    private static double reCalculateWithPawnNeighbors(double totalScore, Map<Column, Integer> pawnNeighbors) {
        for (Column col : pawnNeighbors.keySet()) {
            totalScore = calculateScoreWithPawnCount(totalScore, pawnNeighbors.get(col));
        }
        return totalScore;
    }

    private static double calculateScoreWithPawnCount(double totalScore, int pawnCount) {
        if (pawnCount >= PAWN_HALF_SCORE_BOUND) {
            totalScore -= pawnCount * SAME_COLUMN_PAWN_SCORE;
        }
        return totalScore;
    }

    public double getTotalScore() {
        return totalScore;
    }
}
