package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.piece.Piece;
import model.position.Position;

public class Result {

    private final Map<Camp, Double> result;

    public Result(Map<Position, Piece> board) {
        result = calculateResult(board);
    }

    private Map<Camp, Double> calculateResult(Map<Position, Piece> board) {
        Map<Camp, Double> res = new HashMap<>();
        res.put(Camp.BLACK, getCount(board, Camp.BLACK));
        res.put(Camp.WHITE, getCount(board, Camp.WHITE));
        return res;
    }

    private double getCount(Map<Position, Piece> board, Camp camp) {
        double total = board.values().stream()
                .filter(piece -> piece.isSameCamp(camp))
                .map(Piece::getScore)
                .reduce(0D, Double::sum);
        return total - pawnsScore(board, camp);
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

    public Map<Camp, Double> getResult() {
        return result;
    }

    public Camp getWinner() {
        if (result.get(Camp.BLACK) > result.get(Camp.WHITE)) {
            return Camp.BLACK;
        }
        return Camp.WHITE;
    }
}
