package chess.domain.game;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {

    public static final String DRAW = "무";
    public static final String WIN = "승";
    public static final String LOSE = "패";
    private static final int COLUMN_NEIGHBOR_PAWN = 2;
    private static final double PAWN_SCORE_PUNISHMENT_RATIO = 0.5;
    private final Map<Position, Piece> chessBoard;

    public Result(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public String winOrLose(Color color) {
        if (score(color) > score(color.getOppositeColor())) {
            return WIN;
        }
        if (score(color) == score(color.getOppositeColor())) {
            return DRAW;
        }
        return LOSE;
    }

    public double score(Color color) {
        double score = normalScore(color);
        Map<Column, Long> pawnCount = pawnCount(color);
        double punishmentScore = pawnScore(pawnCount);
        return score - punishmentScore;
    }

    private double normalScore(Color color) {
        return chessBoard.values().stream()
            .filter(piece -> piece.isSameColor(color))
            .mapToDouble(Piece::score)
            .sum();
    }

    private Map<Column, Long> pawnCount(Color color) {
        return chessBoard.entrySet()
            .stream()
            .filter(piece -> piece.getValue().isPawn())
            .filter(piece -> piece.getValue().isSameColor(color))
            .collect(Collectors
                .groupingBy(position -> position.getKey().getColumn(), Collectors.counting()));
    }

    private double pawnScore(Map<Column, Long> pawnCount) {
        return pawnCount.values().stream()
            .filter(count -> count >= COLUMN_NEIGHBOR_PAWN)
            .mapToDouble(count -> count * PAWN_SCORE_PUNISHMENT_RATIO)
            .sum();
    }
}
