package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class GameResult {
    private static final int PAWN_CRITICAL_COUNT = 2;
    private static final int DIVIDEND_WHEN_MULTIPLE_PAWN_IN_COLUMN = 2;

    public double calculateScore(final Board board, final Team team) {
        double totalScore = 0;

        for (int col = Position.START_INDEX; col <= Position.END_INDEX; col++) {
            int pawnCount = 0;
            double pawnScore = 0;

            for (int row = Position.START_INDEX; row <= Position.END_INDEX; row++) {
                Piece piece = board.findPieceBy((row - 1) * Position.ROW_SIZE + col - 1);

                if (piece.isSameTeam(team) && piece.isPawn()) {
                    pawnCount += 1;
                    pawnScore += piece.score();
                }

                if (piece.isSameTeam(team) && !piece.isPawn()) {
                    totalScore += piece.score();
                }
            }

            if (pawnCount >= PAWN_CRITICAL_COUNT) {
                totalScore += pawnScore / DIVIDEND_WHEN_MULTIPLE_PAWN_IN_COLUMN;
                continue;
            }

            totalScore += pawnScore;
        }
        return totalScore;
    }
}
