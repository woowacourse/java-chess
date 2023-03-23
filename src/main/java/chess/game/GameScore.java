package chess.game;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.domain.piece.Piece;

import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class GameScore {

    private static final int PAWN_SCORE_DECREASE_COUNT = 2;
    private static final int COUNT_OF_COLUMN = 8;

    private GameScore() {
    }

    public static Double calculateScore(Map<Position, Piece> squares, Team team) {
        double score = 0;
        for (int column = 0; column < COUNT_OF_COLUMN; column++) {
            score += calculateScoreByColumn(squares, team, column);
        }
        return score;
    }

    private static double calculateScoreByColumn(Map<Position, Piece> squares, Team team, int column) {
        Map<Role, Long> pieceCount = IntStream.range(0, COUNT_OF_COLUMN)
                .filter(row -> squares.get(Position.of(column, row)).isSameTeam(team))
                .mapToObj(row -> squares.get(Position.of(column, row)))
                .collect(groupingBy(Piece::getRole, counting()));

        double columnScore = 0;
        columnScore = calculateColumnScore(pieceCount, columnScore);
        return columnScore;
    }

    private static double calculateColumnScore(Map<Role, Long> pieceCount, double columnScore) {
        for (Map.Entry<Role, Long> entry : pieceCount.entrySet()) {
            Role role = entry.getKey();
            Long count = entry.getValue();
            columnScore += calculateScoreByRole(role, count);
        }
        return columnScore;
    }

    private static double calculateScoreByRole(Role role, Long count) {
        if (role != Role.PAWN) {
            return role.getScore() * count;
        }

        if (count >= PAWN_SCORE_DECREASE_COUNT) {
            return (role.getScore() / 2) * count;
        }
        return role.getScore();
    }
}
