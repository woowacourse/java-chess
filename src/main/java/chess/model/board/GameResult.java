package chess.model.board;

import static chess.model.Team.BLACK;
import static chess.model.Team.NONE;
import static chess.model.Team.WHITE;

import chess.model.Team;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class GameResult {

    public static final double PAWN_PENALTY_SCORE = -0.5;
    public static final int DUPLICATE_CHECK_PAWN_COUNT = 2;
    private final Map<Team, Double> scores = new HashMap<>();

    public void createScoreResult(final Map<Position, Piece> board) {
        scores.put(BLACK, calculateScore(BLACK, board) + calculatePawnPenalty(BLACK, board));
        scores.put(WHITE, calculateScore(WHITE, board) + calculatePawnPenalty(WHITE, board));
    }

    private Double calculateScore(Team team, Map<Position, Piece> board) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(Piece::score)
                .sum();
    }

    private Double calculatePawnPenalty(Team team, Map<Position, Piece> board) {
        List<Position> positionOfPawns = searchPositionOfPawns(team, board);
        return PAWN_PENALTY_SCORE * searchPenaltyPawns(positionOfPawns);
    }

    private List<Position> searchPositionOfPawns(Team team, Map<Position, Piece> board) {
        return board.keySet().stream()
                .filter(position -> board.get(position).isSameTeam(team))
                .filter(position -> board.get(position).isPawn())
                .collect(Collectors.toList());
    }

    private long searchPenaltyPawns(List<Position> positionOfPawns) {
        return positionOfPawns.stream()
                .flatMapToLong(positionOfPawn -> calculateSameRankCount(positionOfPawn, positionOfPawns))
                .filter(count -> count > 1)
                .sum() / DUPLICATE_CHECK_PAWN_COUNT;
    }

    private LongStream calculateSameRankCount(Position position, List<Position> positionOfPawns) {
        return LongStream.of(positionOfPawns.stream()
                .filter(otherPosition -> otherPosition.isSameFile(position)).count());
    }

    public Map<Team, Double> getScores() {
        return Collections.unmodifiableMap(scores);
    }

    public Team pickWinner() {
        if (scores.get(BLACK) > scores.get(WHITE)) {
            return BLACK;
        }
        if (scores.get(BLACK) < scores.get(WHITE)) {
            return WHITE;
        }
        return NONE;
    }
}
