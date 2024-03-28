package chess.domain.score;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.List;

public class ScoreManager {
    private static final double OPTIONAL_PAWN_SCORE = 0.5;

    public Score calculateFileScore(List<Piece> pieces) {
        Score baseScore = calculateBaseScore(pieces);
        int pawnCount = calculatePawnCount(pieces);
        if (pawnCount >= 2) {
            return baseScore.subtract(pawnCount * OPTIONAL_PAWN_SCORE);
        }
        return baseScore;
    }

    private Score calculateBaseScore(List<Piece> pieces) {
        return pieces.stream()
                .map(Piece::score)
                .reduce(new Score(0), Score::add);
    }

    private int calculatePawnCount(List<Piece> pieces) {
        return (int) pieces.stream()
                .filter(piece -> piece.isTypeOf(PieceType.pawns()))
                .count();
    }
}
