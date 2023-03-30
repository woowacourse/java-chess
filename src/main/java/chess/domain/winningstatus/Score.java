package chess.domain.winningstatus;

import chess.domain.piece.SquareState;

import java.util.List;

public class Score {

    private final double score;

    private Score(final double score) {
        this.score = score;
    }

    public static Score of(final List<SquareState> pieces, final int doublePawnCount) {
        double score = 0.0;

        for (SquareState piece : pieces) {
            score += PieceScore.getScoreByPiece(piece);
        }
        score -= doublePawnCount * 0.5;
        return new Score(score);
    }

    public double getScore() {
        return score;
    }
}
