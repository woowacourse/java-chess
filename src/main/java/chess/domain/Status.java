package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Score;
import chess.domain.piece.Team;

import java.util.Collections;
import java.util.Map;

public class Status {

    public static Map<Team, Double> result(Board board) {
        return Map.of(Team.BLACK, calculateOf(board, Team.BLACK),
                Team.WHITE, calculateOf(board, Team.WHITE));
    }

    private static double calculateOf(Board board, Team team) {
        return board.getColumnGroupOf(team)
                .stream()
                .mapToDouble(Score::calculateScoreOf)
                .sum();
    }

    public static String winner(Board board) {
        Map<Team, Double> status = result(board);
        if (status.get(Team.BLACK).equals(status.get(Team.WHITE))) {
            return "없음 (무승부)";
        }

        double winnerScore = Collections.max(status.values());
        return status.keySet()
                .stream()
                .filter(key -> status.get(key).equals(winnerScore))
                .map(Team::getName)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }
}
