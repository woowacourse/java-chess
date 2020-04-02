package chess.domain.score;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.File;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Score {
    private static final double PAWN_SCORE_IN_SAME_FILE = 0.5d;

    public static double calculateScore(Map<Position, Piece> board, Team team) {
        double totalScore = calculateTotalScore(board, team);
        return calculatePawnScore(board, team, totalScore);
    }

    private static double calculateTotalScore(Map<Position, Piece> board, Team team) {
        return board.values().stream()
                .filter(piece -> team.isSameTeamWith(piece.getTeam()))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private static double calculatePawnScore(Map<Position, Piece> board, Team team, double score) {
        for (File file : File.values()) {
            List<Map.Entry<Position, Piece>> sameFile = board.entrySet().stream()
                    .filter(entry -> File.of(entry.getKey().getFile()).equals(file))
                    .filter(entry -> entry.getValue().isPawn() && !entry.getValue().isEnemy(team))
                    .collect(Collectors.toList());

            if (sameFile.size() > 1) {
                score -= sameFile.size() * PAWN_SCORE_IN_SAME_FILE;
            }
        }
        return score;
    }
}
