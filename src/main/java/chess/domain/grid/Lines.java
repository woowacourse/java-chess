package chess.domain.grid;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lines {
    private static final String ROW_REFERENCE = "87654321";
    private static final char MIN_X_POSITION = 'a';
    private static final int SAME_COLUMN_BOUND = 2;

    private List<Line> lines;

    public Lines(List<Line> lines) {
        this.lines = new ArrayList<>(lines);
    }

    public List<Line> toList() {
        return Collections.unmodifiableList(lines);
    }

    public Line line(final char y) {
        int index = ROW_REFERENCE.indexOf(y);
        return lines.get(index);
    }

    public double pawnCountInSameColumn(final boolean isBlack, final int i) {
        double result = 0;
        char x = (char) (MIN_X_POSITION + i);
        int pawnCountInSameColumn =
                (int) lines.stream()
                        .map(line -> line.findPiece(x))
                        .filter(piece -> (piece instanceof Pawn && piece.isBlack() == isBlack))
                        .count();
        if (pawnCountInSameColumn >= SAME_COLUMN_BOUND) {
            result += pawnCountInSameColumn;
        }
        return result;
    }

    public double totalScore(final boolean isBlack) {
        return lines.stream()
                .flatMap(line -> line.getPieces()
                        .stream()
                        .filter(piece -> !piece.isEmpty())
                        .filter(piece -> piece.isBlack() == isBlack)
                        .map(Piece::score))
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
