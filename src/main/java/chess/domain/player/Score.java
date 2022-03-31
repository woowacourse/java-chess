package chess.domain.player;

import chess.domain.position.File;
import chess.domain.piece.Piece;
import java.util.List;

public class Score {

    private static final int DEFAULT_PLURAL_COUNT = 2;
    private static final double DUPLICATE_PAWN_SCORE = 0.5;

    private final double score;

    public Score(List<Piece> pieces) {
        this.score = calculateAllPieceScore(pieces) - calculateDuplicatePawnScore(pieces);
    }

    private double calculateAllPieceScore(final List<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double calculateDuplicatePawnScore(final List<Piece> pieces) {
        int pawnCountByFile = 0;
        for (File file : File.values()) {
            pawnCountByFile += countPluralPawnByFile(pieces, file);
        }
        return DUPLICATE_PAWN_SCORE * pawnCountByFile;
    }

    private int countPluralPawnByFile(final List<Piece> pieces, final File file) {
        final int count = (int) pieces.stream()
                .filter(piece -> piece.isPawn() && piece.isSameFile(file))
                .count();
        if (count >= DEFAULT_PLURAL_COUNT) {
            return count;
        }
        return 0;
    }

    public double getScore() {
        return score;
    }
}
