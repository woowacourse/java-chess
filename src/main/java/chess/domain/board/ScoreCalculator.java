package chess.domain.board;

import chess.domain.Team;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScoreCalculator {

    private final Map<Position, Piece> board;

    public ScoreCalculator(Map<Position, Piece> board) {
        this.board = board;
    }

    public double calculateScore(Team team) {
        double score = 0;
        for (int column = 1; column <= 8; column++) {
            List<Piece> columnPieces = findColumnPieces(team, column);
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

    private List<Piece> findColumnPieces(Team team, final int column) {
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
