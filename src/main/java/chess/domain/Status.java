package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Team;

import java.util.Collections;
import java.util.Map;

public class Status {

    public static Map<Team, Double> result(Board board) {
        return Map.of(Team.BLACK, board.getScoreOf(Team.BLACK),
                Team.WHITE, board.getScoreOf(Team.WHITE));
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
