package chess.domain.score;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.score.Score;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreCalculator {

    private static final Map<PieceType, Score> pieceScore =
            Map.of(PieceType.King, new Score(0),
                    PieceType.Queen, new Score(9),
                    PieceType.Rook, new Score(5),
                    PieceType.Bishop, new Score(3),
                    PieceType.Knight, new Score(2.5),
                    PieceType.Pawn, new Score(1));
    private static final Score PAWN_WITH_SAME_FILE = new Score(0.5);

    public static Score calculateScore(Map<Position, Piece> board, Team team) {
        Score score = board.keySet().stream()
                .filter(key -> board.get(key).isSameTeam(team))
                .map(key -> pieceScore.get(board.get(key).type()))
                .reduce(Score.min(), Score::add);

        return reCalculateAboutPawn(board, score, team);
    }

    private static Score reCalculateAboutPawn(Map<Position, Piece> board, Score score, Team team) {
        Map<Integer, Long> pawnCount = board.keySet().stream()
                .filter(key -> board.get(key).isSameTeam(team) && board.get(key).getClass() == Pawn.class)
                .collect(Collectors.groupingBy(Position::getFile, Collectors.counting()));

        Long countForRecalculation = pawnCount.values().stream()
                .filter(count -> count > 1)
                .reduce(0L, Long::sum);

        return score.subtract(new Score(PAWN_WITH_SAME_FILE.getScore() * countForRecalculation));
    }
}
