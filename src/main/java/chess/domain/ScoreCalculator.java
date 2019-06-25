package chess.domain;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreCalculator {
    private static final double HALF_SCORE = 0.5;

    private final List<Piece> pieces;

    public ScoreCalculator(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public double getScore(final Piece.Color color) {
        List<Piece> temp = pieces.stream()
                .filter(piece -> piece.isSameColor(color))
                .collect(Collectors.toList());

        return colorScore(temp);
    }

    private double colorScore(List<Piece> pieces) {
        double sum = 0;
        List<Piece> notPawnList = pieces.stream()
                .filter(piece -> !piece.isPawn())
                .collect(Collectors.toList());

        for (final Piece piece : notPawnList) {
            sum += piece.getScore();
        }
        pieces.removeAll(notPawnList);
        sum += sumOfPawn(pieces);
        return sum;
    }

    private double sumOfPawn(List<Piece> pawns) {
        double sum = 0;
        for (int i = Column.MIN; i <= Column.MAX; i++) {
            sum += sumOfPawn(i, pawns);
        }
        return sum;
    }

    private double sumOfPawn(final int column, final List<Piece> pawns) {
        List<Piece> retPawns = pawns.stream()
                .filter(piece -> piece.isSameColumn(Column.from(String.valueOf((char) column))))
                .collect(Collectors.toList());
        if (retPawns.size() >= 2) {
            return retPawns.size() * HALF_SCORE;
        }
        return retPawns.size();
    }
}

