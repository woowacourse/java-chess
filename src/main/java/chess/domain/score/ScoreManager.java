package chess.domain.score;

import chess.domain.piece.Piece;
import java.util.List;

public class ScoreManager {

    public Score calculateFileScore(List<Piece> pieces) {
        Score baseScore = calculateBaseScore(pieces);
        int pawnCount = calculatePawnCount(pieces);
        if (pawnCount >= 2) {
            return baseScore.subtract(pawnCount * 0.5);
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
                .filter(Piece::isPawn)
                .count();
    }
}
