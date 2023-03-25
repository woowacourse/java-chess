package chess.domain.score;

import chess.domain.piece.SquareState;

import java.util.List;

public class Score {

    private final double score;

    public Score(List<SquareState> pieces, int doublePawnCount) {
        double score = 0.0;

        for (SquareState piece : pieces) {
            score += PieceScore.getScoreByPiece(piece);
        }
        score -= doublePawnCount * 0.5;

        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
