package chess.domain.result;

import chess.domain.board.Coordinate;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.TeamType;

import java.util.Arrays;
import java.util.Map;

public class Score {
    private static final double MINIMUM_SCORE = 0.0;

    private final double score;

    public Score(double score) {
        validateScore(score);
        this.score = score;
    }

    public static Score from(Map<Coordinate, Piece> pieces) {
        double scoreTotalExceptPawn = calculateScoreTotalExceptPawn(pieces);
        double pawnScoreTotal = 0;
        for (File file : File.values()) {
            int pawnCounts = calculatePawnCountsByFile(pieces, file);
            pawnScoreTotal += accumulatePawnScore(pawnCounts);
        }
        return new Score(scoreTotalExceptPawn + pawnScoreTotal);
    }

    private static double calculateScoreTotalExceptPawn(Map<Coordinate, Piece> pieces) {
        return pieces.values()
                .stream()
                .filter(piece -> !piece.isPawn())
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private static int calculatePawnCountsByFile(Map<Coordinate, Piece> pieces, File file) {
        return (int) Arrays.stream(Rank.values())
                .map(rank -> new Coordinate(file, rank))
                .filter(pieces::containsKey)
                .filter(coordinate -> pieces.get(coordinate).isPawn())
                .count();
    }

    private static double accumulatePawnScore(int pawnCounts) {
        if (pawnCounts == 0) {
            return 0;
        }
        Piece pawn = new Pawn(TeamType.WHITE);
        if (pawnCounts > 1) {
            return (pawn.getScore() / 2) * pawnCounts;
        }
        return pawn.getScore();

    }

    private void validateScore(double score) {
        if (score < MINIMUM_SCORE) {
            throw new IllegalArgumentException("점수는 0 이상이어야 합니다.");
        }
    }

    public double getScore() {
        return score;
    }
}
