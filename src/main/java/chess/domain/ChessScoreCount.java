package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessScoreCount {

    private Map<Team, Double> scores;

    public ChessScoreCount(List<PieceType> chessPieces) {
        scores = new HashMap<>();
        scores.put(Team.BLACK, calculateScoreByTeam(chessPieces, Team.BLACK));
        scores.put(Team.WHITE, calculateScoreByTeam(chessPieces, Team.WHITE));
    }

    private double calculateScoreByTeam(List<PieceType> chessPieces, Team teamToCalculate) {
        return chessPieces.stream()
                .filter(p -> p.getTeam() == teamToCalculate)
                .mapToDouble(PieceType::getScore)
                .sum();
    }

    public double getScore(Team team) {
        return scores.get(team);
    }
}
