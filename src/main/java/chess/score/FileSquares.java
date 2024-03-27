package chess.score;

import chess.board.Square;
import chess.piece.Color;
import java.util.List;

public class FileSquares {

    private static final int MIN_PAWN_COUNT_FOR_MANIPULATION = 2;

    private final List<Square> pieces;

    public FileSquares(List<Square> squares) {
        this.pieces = squares;
    }

    public Score calculateScore(Color color) {
        Score scoreWithoutPawn = calculateScoreWithoutPawn(color);
        Score pawnScore = calculatePawnScore(color);
        return scoreWithoutPawn.add(pawnScore);
    }

    private Score calculateScoreWithoutPawn(Color color) {
        return pieces.stream()
                .filter(square -> square.hasPieceColored(color))
                .filter(Square::hasNoPawn)
                .map(Square::getScore)
                .reduce(Score.ZERO, Score::add);
    }

    private Score calculatePawnScore(Color color) {
        int pawnCount = (int) pieces.stream()
                .filter(square -> square.hasPieceColored(color))
                .filter(Square::hasPawn)
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
