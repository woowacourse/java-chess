package chess.score;

import chess.piece.Color;
import chess.piece.Piece;
import java.util.List;

public class FilePieces {

    private static final int MIN_PAWN_COUNT_FOR_MANIPULATION = 2;

    private final List<Piece> pieces;

    public FilePieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Score calculateScore(Color color) {
        Score scoreWithoutPawn = calculateScoreWithoutPawn(color);
        Score pawnScore = calculatePawnScore(color);
        return scoreWithoutPawn.add(pawnScore);
    }

    private Score calculateScoreWithoutPawn(Color color) {
        return pieces.stream()
                .filter(Piece::isNotPawn)
                .filter(piece -> piece.hasColorOf(color))
                .map(Piece::getScore)
                .reduce(Score.ZERO, Score::add);
    }

    private Score calculatePawnScore(Color color) {
        int pawnCount = (int) pieces.stream()
                .filter(Piece::isPawn)
                .filter(piece -> piece.hasColorOf(color))
                .count();
        return manipulateScoreByPawnCount(pawnCount);
    }

    private Score manipulateScoreByPawnCount(int pawnCount) {
        Score score = PieceScore.PAWN.asScore();
        Score pawnScore = score.multiplyBy(pawnCount);
        if (pawnCount < MIN_PAWN_COUNT_FOR_MANIPULATION) {
            return pawnScore;
        }
        return pawnScore.divideInHalf();
    }
}
