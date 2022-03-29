package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.postion.Position;

import java.util.HashMap;
import java.util.Map;

public class Score {

    private final Map<Team, Double> result;

    private Score(Map<Team, Double> result) {
        this.result = result;
    }

    public static Score from(final Board board) {
        Map<Team, Double> res = new HashMap<>();
        res.put(Team.BLACK, calculateScore(Team.BLACK, board));
        res.put(Team.WHITE, calculateScore(Team.WHITE, board));

        return new Score(res);
    }

    private static double calculateScore(final Team team, final Board board) {
        final Map<Position, Piece> cells = board.cells();

        return cells.values()
                .stream()
                .filter(piece -> team.equals(piece.team()))
                .mapToDouble(Piece::score)
                .sum();
    }

    public double whiteScore() {
        return result.get(Team.WHITE);
    }

    public double blackScore() {
        return result.get(Team.BLACK);
    }
}
