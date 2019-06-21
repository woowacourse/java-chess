package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChessScoreCount {

    private Map<Team, Double> scores;

    public ChessScoreCount(Set<PieceType> chessPieces) {
        scores = new HashMap<>();
        scores.put(Team.BLACK, calculateScoreByTeam(chessPieces, Team.BLACK));
        scores.put(Team.WHITE, calculateScoreByTeam(chessPieces, Team.WHITE));
    }

    private double calculateScoreByTeam(Set<PieceType> chessPieces, Team teamToCalculate) {
        return chessPieces.stream()
                .filter(p -> p.getTeam() == teamToCalculate)
                .mapToDouble(PieceType::getScore)
                .sum();
    }

    public double getScore(Team team) {
        return scores.get(team);
    }
}
