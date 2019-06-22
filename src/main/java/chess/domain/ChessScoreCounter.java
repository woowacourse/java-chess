package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class ChessScoreCounter {
    private Map<Team, Double> scores;

    public ChessScoreCounter(Map<CoordinatePair, PieceType> boardState) {
        scores = new HashMap<>();
        scores.put(Team.BLACK, calculateScoreByTeam(boardState, Team.BLACK));
        scores.put(Team.WHITE, calculateScoreByTeam(boardState, Team.WHITE));
    }

    private double calculateScoreByTeam(Map<CoordinatePair, PieceType> boardState, Team teamToCalculate) {
        double pawnScore = countAndCalculatePawnScore(boardState, teamToCalculate);
        return boardState.values().stream()
            .filter(pt -> pt.getTeam() == teamToCalculate)
            .filter(pt -> pt != PieceType.PAWN_WHITE)
            .filter(pt -> pt != PieceType.PAWN_BLACK)
            .mapToDouble(PieceType::getScore)
            .sum() + pawnScore;
    }

    private double countAndCalculatePawnScore(Map<CoordinatePair, PieceType> boardState, Team teamToCalculate) {
        return CoordinateX.allAscendingCoordinates().stream()
            .map(x -> countPawn(boardState, teamToCalculate, x))
            .mapToDouble(this::calculatePawnScore)
            .sum();
    }

    private double calculatePawnScore(int numOfPawnsOnSameColumn) {
        if (numOfPawnsOnSameColumn == 1) {
            return PieceType.PAWN_BLACK.getScore();
        }
        return (PieceType.PAWN_BLACK.getScore() / 2) * numOfPawnsOnSameColumn;
    }

    private int countPawn(Map<CoordinatePair, PieceType> boardState, Team teamToCalculate, CoordinateX x) {
        if (teamToCalculate == Team.WHITE) {
            return (int) (CoordinateY.allAscendingCoordinates().stream()
                .filter(y -> boardState.get(CoordinatePair.of(x, y)) == PieceType.PAWN_WHITE)
                .count());
        }
        return (int) (CoordinateY.allAscendingCoordinates().stream()
            .filter(y -> boardState.get(CoordinatePair.of(x, y)) == PieceType.PAWN_BLACK)
            .count());
    }

    public double getScore(Team team) {
        return scores.get(team);
    }
}
