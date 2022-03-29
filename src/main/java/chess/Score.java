package chess;

import chess.piece.Color;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.position.File;
import chess.position.Position;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

public class Score {

    public static final BigDecimal PAWN_DEDUCT_POINT = new BigDecimal("0.5");

    private final BigDecimal value;

    public Score(Map<Position, Piece> board, Color color) {
        this.value = getDefaultScore(board, color).subtract(getDeductPointOfPawn(board, color));
    }

    private BigDecimal getDefaultScore(Map<Position, Piece> board, Color color) {
        return board.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .map(Piece::getPoint)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getDeductPointOfPawn(Map<Position, Piece> board, Color color) {
        return PAWN_DEDUCT_POINT.multiply(new BigDecimal(numberOfPawn(board, color)));
    }

    private long numberOfPawn(Map<Position, Piece> board, Color color) {
        return Arrays.stream(File.values())
                .map(file -> numberOfEachFilePawn(board, color, file))
                .filter(numberOfPawn -> numberOfPawn >= 2)
                .reduce(0L, Long::sum);
    }

    private long numberOfEachFilePawn(Map<Position, Piece> board, Color color, File file) {
        return board.keySet().stream()
                .filter(position -> position.isSameFile(file))
                .map(board::get)
                .filter(piece -> piece.equals(new Pawn(color)))
                .filter(piece -> piece.isSameColor(color))
                .count();
    }

    public BigDecimal getValue() {
        return value;
    }
}
