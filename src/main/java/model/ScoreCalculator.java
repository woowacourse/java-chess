package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.piece.Piece;
import model.position.Position;

public class ScoreCalculator {

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
            return 0;
        }
        double total = pieces.stream()
                .map(Piece::getScore)
                .reduce(0D, Double::sum);
        return total - pawnsScore(board, camp);
    }

    private boolean isKingDie(List<Piece> pieces) {
        return pieces.stream().noneMatch(Piece::isKing);
    }

    private double pawnsScore(Map<Position, Piece> board, Camp camp) {
        List<Position> pawnPositions = board.keySet().stream()
                .filter(position -> board.get(position).isSameCamp(camp))
                .filter(position -> board.get(position).isPawn())
                .toList();
        return sameColumnPawnsCount(pawnPositions) * 0.5;
    }

    private int sameColumnPawnsCount(List<Position> pawnPositions) {
        int count = 0;
        for (Position pawnPosition : pawnPositions) {
            count += sameColumnPawnCount(pawnPosition, pawnPositions);
        }
        return count;
    }

    private int sameColumnPawnCount(Position pawnPosition, List<Position> pawnPositions) {
        int count = 0;
        for (Position position : pawnPositions) {
            count += getCount(pawnPosition, position);
        }
        return count;
    }

    private static int getCount(Position pawnPosition, Position position) {
        if (!pawnPosition.equals(position) && pawnPosition.isSameColumn(position)) {
            return 1;
        }
        return 0;
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
