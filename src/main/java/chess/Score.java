package chess;

import chess.piece.Piece;
import chess.position.File;
import java.math.BigDecimal;
import java.util.*;

public class Score {

    public static final BigDecimal PAWN_DEDUCT_POINT = new BigDecimal("0.5");

    private final BigDecimal value;

    public Score(List<Piece> pieces) {
        this.value = getDefaultScore(pieces).subtract(getDeductPointOfPawn(pieces));
    }

    public BigDecimal getValue() {
        return value;
    }

    private BigDecimal getDefaultScore(List<Piece> pieces) {
        return pieces.stream()
            .map(Piece::getPoint)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getDeductPointOfPawn(List<Piece> pieces) {
        return PAWN_DEDUCT_POINT.multiply(new BigDecimal(numberOfPawn(pieces)));
    }

    private long numberOfPawn(List<Piece> pieces) {
        return Arrays.stream(File.values())
            .mapToLong(file -> numberOfPawnEachFile(pieces, file))
            .filter(numberOfPawn -> numberOfPawn > 1)
            .sum();
    }

    private long numberOfPawnEachFile(List<Piece> pieces, File file) {
        return pieces.stream()
            .filter(Piece::isPawn)
            .filter(piece -> piece.isSameFile(file))
            .count();
    }
}
