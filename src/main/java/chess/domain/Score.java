package chess.domain;

import java.util.EnumMap;
import java.util.Map;

public class Score {

    private double totalScore;

    public Score(Map<Row, Rank> board, Team team) {
        getTeamScore(board, team);
    }

    public double getTeamScore(Map<Row, Rank> board, Team team) {
        Map<Column, Integer> pawnNeighbors = new EnumMap<>(Column.class);
        for (Row row : board.keySet()) {
            totalScore += board.get(row).calculateRankTotalScore(team);
            findNeighborPawns(board.get(row), team, pawnNeighbors);
        }
        return reCalculateWithPawnNeighbors(pawnNeighbors);
    }

    private double reCalculateWithPawnNeighbors(Map<Column, Integer> pawnNeighbors) {
        for (Column col : pawnNeighbors.keySet()) {
            totalScore = calculateScoreWithPawnCount(pawnNeighbors.get(col));
        }
        return totalScore;
    }

    private double calculateScoreWithPawnCount(int pawnCount) {
        if (pawnCount > 1) {
            totalScore -= pawnCount * 0.5;
        }
        return totalScore;
    }

    private void findNeighborPawns(Rank rank, Team team, Map<Column, Integer> pawnNeighbors) {
        for (Column column : Column.values()) {
            findNeighborsInColumn(rank, team, column, pawnNeighbors);
        }
    }

    private void findNeighborsInColumn(Rank rank, Team team, Column column, Map<Column, Integer> pawnNeighbors) {
        if (rank.isSameTeamPawn(team, column)) {
            pawnNeighbors.put(column, pawnNeighbors.getOrDefault(column, 0) + 1);
        }
    }

    public double getTotalScore() {
        return totalScore;
    }
}
