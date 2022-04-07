package chess.domain.board;

import chess.domain.Team;
import chess.domain.Winner;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreCalculator {

    private static final ScoreCalculator instance = new ScoreCalculator();

    private ScoreCalculator() {
    }

    public static ScoreCalculator of() {
        return instance;
    }

    public double calculateBlackScore(Map<Position, Piece> board) {
        return calculateScore(board, Team.BLACK);
    }

    public double calculateWhiteScore(Map<Position, Piece> board) {
        return calculateScore(board, Team.WHITE);
    }

    public Winner calculateWinner(Map<Position, Piece> board) {
        double blackScore = calculateBlackScore(board);
        double whiteScore = calculateWhiteScore(board);
        return Winner.find(blackScore, whiteScore);
    }

    private double calculateScore(Map<Position, Piece> board, Team team) {
        double score = 0;
        for (int column = 1; column <= 8; column++) {
            List<Piece> columnPieces = findColumnPieces(board, team, column);
            score += calculateColumnScore(columnPieces);
        }
        return score;
    }

    private double calculateColumnScore(final List<Piece> columnPieces) {
        double sum = 0;
        long pawnCount = columnPieces.stream()
                .filter(Piece::isPawn)
                .count();
        for (Piece piece : columnPieces) {
            sum += piece.getScore();
        }
        sum = calculatePawnScore(sum, pawnCount);
        return sum;
    }

    private double calculatePawnScore(double sum, long pawnCount) {
        if (pawnCount >= 2) {
            sum -= 0.5 * pawnCount;
        }
        return sum;
    }

    private List<Piece> findColumnPieces(Map<Position, Piece> board, Team team, final int column) {
        List<Piece> pieces = new ArrayList<>();
        for (int row = 1; row <= 8; row++) {
            Position position = Position.of(row, column);
            if (board.get(position).isSameTeam(team)) {
                pieces.add(board.get(position));
            }
        }
        return pieces;
    }
}
