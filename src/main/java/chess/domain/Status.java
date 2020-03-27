package chess.domain;

import chess.domain.board.Boards;

import java.util.Collections;
import java.util.Map;

public class Status {

    public static Map<Turn, Double> result(Boards boards) {
        return Map.of(Turn.LOWER, boards.getScoreOf(Turn.LOWER),
                Turn.UPPER, boards.getScoreOf(Turn.UPPER));
    }

    public static String winner(Boards boards) {
        Map<Turn, Double> status = result(boards);
        if (status.get(Turn.UPPER).equals(status.get(Turn.LOWER))) {
            return "없음 (무승부)";
        }

        double winnerScore = Collections.max(status.values());
        return status.keySet()
                .stream()
                .filter(key -> status.get(key).equals(winnerScore))
                .map(Turn::getName)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }
}
