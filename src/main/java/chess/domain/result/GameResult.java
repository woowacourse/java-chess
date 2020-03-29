package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class GameResult {
    public double calculateScore(Board board, Team team) {

        double totalScore = 0;

        for (int col = 1; col <= 8; col++) {

            int pawnCount = 0;
            double pawnScore = 0;
            for (int row = 1; row <= 8; row++) {
                Piece piece = board.findPieceBy(8 * (row - 1) + col - 1);

                if (piece.isSameTeam(team) && piece.isPawn()) {
                    pawnCount += 1;
                    pawnScore += PieceType.getScoreOf(piece);
                }

                if (piece.isSameTeam(team) && !piece.isPawn()) {
                    totalScore += PieceType.getScoreOf(piece);
                }
            }
            if (pawnCount >= 2) {
                totalScore += pawnScore / 2;
                continue;
            }
            totalScore += pawnScore;
        }
        return totalScore;
    }
}
