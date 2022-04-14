package chess.model.board.result;

import static chess.model.Team.BLACK;
import static chess.model.Team.WHITE;

import chess.model.Team;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Score {

    private static final double PAWN_PENALTY_SCORE = -0.5;
    private static final int DUPLICATE_CHECK_PAWN_COUNT = 2;

    private final Map<Position, Piece> board;

    public Score(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Team, Double> teams() {
        Map<Team, Double> scores = new HashMap<>();
        scores.put(BLACK, team(BLACK, board) + pawnPenalty(BLACK, board));
        scores.put(WHITE, team(WHITE, board) + pawnPenalty(WHITE, board));
        return scores;
    }

    public Double white() {
        return team(WHITE, board) + pawnPenalty(WHITE, board);
    }

    public Double black() {
        return team(BLACK, board) + pawnPenalty(BLACK, board);
    }

    private Double team(Team team, Map<Position, Piece> board) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(Piece::score)
                .sum();
    }

    private Double pawnPenalty(Team team, Map<Position, Piece> board) {
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
}
