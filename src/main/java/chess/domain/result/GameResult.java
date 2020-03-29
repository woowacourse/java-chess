package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class GameResult {
    private static final int INITIAL_SCORE = 0;
    private static final int START_INDEX = 1;
    private static final int END_INDEX = 8;
    private static final int ROW_SIZE = 8;
    private static final int INITIAL_PAWN_COUNT = 0;
    private static final int INITIAL_PAWN_ROW_SCORE = 0;
    private static final int PAWN_COUNT_INCREASE = 1;
    private static final int PAWN_CRITICAL_COUNT = 2;
    private static final int DIVIDEND_WHEN_MULITPLE_PAWN_IN_COLUMN = 2;

    public double calculateScore(Board board, Team team) {

        double totalScore = INITIAL_SCORE;

        for (int col = START_INDEX; col <= END_INDEX; col++) {
            int pawnCount = INITIAL_PAWN_COUNT;
            double pawnScore = INITIAL_PAWN_ROW_SCORE;

            for (int row = START_INDEX; row <= END_INDEX; row++) {
                Piece piece = board.findPieceBy( (row - 1) * ROW_SIZE + col - 1);

                if (piece.isSameTeam(team) && piece.isPawn()) {
                    pawnCount += PAWN_COUNT_INCREASE;
                    pawnScore += PieceType.getScoreOf(piece);
                }

                if (piece.isSameTeam(team) && !piece.isPawn()) {
                    totalScore += PieceType.getScoreOf(piece);
                }
            }
            if (pawnCount >= PAWN_CRITICAL_COUNT) {
                totalScore += pawnScore / DIVIDEND_WHEN_MULITPLE_PAWN_IN_COLUMN;
                continue;
            }
            totalScore += pawnScore;
        }
        return totalScore;
    }
}
