package chess.domain.board;

import java.util.Arrays;
import java.util.Map;

public class BoardScore {

    private static final int HALF_SCORE_BOUND = 2;
    private static final double HALF_PAWN_SCORE = 0.5;
            
    private final Map<Point, Square> board;

    public BoardScore(Map<Point, Square> board) {
        this.board = board;
    }

    public double score(Team team) {
        return sumScore(team) - (HALF_PAWN_SCORE * pawnCountInSameColumn(team));
    }

    private long pawnCountInSameColumn(Team team) {
        return Arrays.stream(Column.values())
                .mapToLong(column -> pawnCountInColumn(team, column))
                .filter(count -> count >= HALF_SCORE_BOUND)
                .sum();
    }

    private long pawnCountInColumn(Team team, Column column) {
        return board.keySet().stream()
                .filter(point -> point.isColumn(column))
                .map(board::get)
                .filter(square -> square.isTeam(team) && square.isPawn())
                .count();
    }

    private double sumScore(Team team) {
        return board.values().stream()
                .filter(square -> square.isTeam(team))
                .mapToDouble(Square::score)
                .sum();
    }
}
