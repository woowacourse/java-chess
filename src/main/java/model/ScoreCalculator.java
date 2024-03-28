package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.piece.Piece;
import model.position.Column;
import model.position.Position;

public class ScoreCalculator {

    private static final double EQUAL_COLUMN_PAWN_SCORE = 0.5;

    private final Map<Camp, Double> result;

    public ScoreCalculator(Map<Position, Piece> board) {
        result = calculateResult(board);
    }

    private Map<Camp, Double> calculateResult(Map<Position, Piece> board) {
        Map<Camp, Double> res = new HashMap<>();
        res.put(Camp.BLACK, getCount(board, Camp.BLACK));
        res.put(Camp.WHITE, getCount(board, Camp.WHITE));
        return res;
    }

    private double getCount(Map<Position, Piece> board, Camp camp) {
        List<Piece> pieces = board.values().stream()
                .filter(piece -> piece.isSameCamp(camp))
                .toList();
        if (isKingDie(pieces)) {
            return 0D;
        }
        double total = pieces.stream()
                .map(piece -> piece.getPieceType().getScore())
                .reduce(0D, Double::sum);
        return total - calculateMinusPawnScore(board, camp);
    }

    private boolean isKingDie(List<Piece> pieces) {
        return pieces.stream().noneMatch(Piece::isKing);
    }

    private double calculateMinusPawnScore(Map<Position, Piece> board, Camp camp) {
        Map<Column, Long> pawnPositions = board.keySet().stream()
                .filter(position -> board.get(position).isSameCamp(camp))
                .filter(position -> board.get(position).isPawn())
                .collect(Collectors.groupingBy(Position::getColumn, Collectors.counting()));
        return calculateSameColumnPawnCount(pawnPositions) * EQUAL_COLUMN_PAWN_SCORE;
    }

    private int calculateSameColumnPawnCount(Map<Column, Long> pawnPositions) {
        int count = 0;
        for (long sameColumnPawnCount : pawnPositions.values()) {
            if (sameColumnPawnCount >= 2) {
                count += (int) sameColumnPawnCount;
            }
        }
        return count;
    }

    public Result getWinner() {
        if (result.get(Camp.BLACK) > result.get(Camp.WHITE)) {
            return Result.BLACK_WIN;
        }
        if (result.get(Camp.BLACK) < result.get(Camp.WHITE)) {
            return Result.WHITE_WIN;
        }
        return Result.DRAW;
    }

    public Map<Camp, Double> getResult() {
        return result;
    }
}
