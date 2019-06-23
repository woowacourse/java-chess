package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreCalculator {
    private static final char START_COLUMN = 'a';
    private static final char LAST_COLUMN = 'h';

    private final List<Square> squares;

    public ScoreCalculator(final List<Square> squares) {
        this.squares = new ArrayList<>(squares);
    }

    public double getScore(final Piece.Color color) {
        List<Square> temp = squares.stream()
                .filter(square -> square.isSameColor(color))
                .collect(Collectors.toList());

        return colorScore(temp);
    }

    private double colorScore(final List<Square> squares) {
        double sum = 0;
        List<Square> pawnList = squares.stream()
                .filter(square -> square.isPawn())
                .collect(Collectors.toList());
        sum += sumOfPawn(squares);

        List<Square> others = squares.stream()
                .filter(square -> !pawnList.contains(square))
                .collect(Collectors.toList());
        for (final Square square : others) {
            sum += square.getScore();
        }

        return sum;
    }


    private double sumOfPawn(List<Square> pawns) {
        double sum = 0;
        for (int i = START_COLUMN; i <= LAST_COLUMN; i++) {
            sum += sumOfPawn(i, pawns);
        }
        return sum;
    }

    private double sumOfPawn(final int column, final List<Square> pawns) {
        List<Square> retPawns = pawns.stream()
                .filter(square -> square.isSameColumn(Column.from(String.valueOf((char) column))))
                .collect(Collectors.toList());
        if (retPawns.size() >= 2) {
            return retPawns.size() * 0.5;
        }
        return retPawns.size();
    }
}

