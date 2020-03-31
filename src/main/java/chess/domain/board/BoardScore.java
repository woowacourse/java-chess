package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class BoardScore {
    private static final int PAWN_ON_SAME_FILE = 1;
    private static final double PAWN_SCORE_STRATEGY = 0.5;

    private final double boardScore;

    public BoardScore(final double boardScore) {
        this.boardScore = boardScore;
    }

    public BoardScore pawnStrategy(List<Map.Entry<Position, Piece>> sameFilePawns) {
        int pawnsCount = sameFilePawns.size();
        if (pawnsCount > PAWN_ON_SAME_FILE) {
            return new BoardScore(this.boardScore - (pawnsCount * PAWN_SCORE_STRATEGY));
        }
        return this;
    }

    public double getBoardScore() {
        return this.boardScore;
    }
}
