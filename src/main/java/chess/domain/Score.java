package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.postion.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Score {

    private static final int CONDITION_FOR_PAWN_SCORE_HALF = 2;

    private final Map<Team, Double> result;

    private Score(final Map<Team, Double> result) {
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
        final Map<Position, Piece> pawns = getPawns(team, cells);
        final List<Piece> piecesExceptPawns = getPiecesExceptPawns(team, cells);

        return calculatePawnsScore(pawns) + calculatePiecesExceptPawnsScore(piecesExceptPawns);
    }

    private static Map<Position, Piece> getPawns(final Team team, final Map<Position, Piece> cells) {
        return cells.keySet()
                .stream()
                .filter(position
                        -> team.equals(cells.get(position).team())
                        && cells.get(position) instanceof Pawn)
                .collect(Collectors.toMap(position -> position, cells::get));
    }

    private static List<Piece> getPiecesExceptPawns(final Team team, final Map<Position, Piece> cells) {
        return cells.values()
                .stream()
                .filter(piece -> team.equals(piece.team()) && !(piece instanceof Pawn))
                .collect(Collectors.toList());
    }

    private static double calculatePawnsScore(final Map<Position, Piece> pawns) {
        double score = 0.0;

        final List<Position> positions = new ArrayList<>(pawns.keySet());

        for (Position position : pawns.keySet()) {
            score += decidePawnScore(positions, position);
        }

        return score;
    }

    private static double decidePawnScore(final List<Position> positions, final Position position) {
        if (isBePawnScoreHalf(positions, position)) {
            return Pawn.HALF_SCORE;
        }

        return Pawn.SCORE;
    }

    private static boolean isBePawnScoreHalf(final List<Position> positions, final Position position) {
        int count = (int) positions.stream()
                .filter(it -> it.isSameFile(position))
                .count();

        return count >= CONDITION_FOR_PAWN_SCORE_HALF;
    }

    private static double calculatePiecesExceptPawnsScore(final List<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(Piece::score)
                .sum();
    }

    public double whiteScore() {
        return result.get(Team.WHITE);
    }

    public double blackScore() {
        return result.get(Team.BLACK);
    }

    public Map<Team, Double> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Score" +
                "result=" + result;
    }
}
