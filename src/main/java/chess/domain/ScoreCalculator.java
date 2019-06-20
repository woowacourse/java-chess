package chess.domain;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreCalculator {
    private final List<Square> squares;

    public ScoreCalculator(final List<Square> squares) {
        this.squares = squares;
    }

    public double getScore(final Piece.Color color) {
        List<Square> temp = squares.stream()
                .filter(square -> square.isSameColor(color))
                .collect(Collectors.toList());

        return colorScore(temp);
    }

    private double colorScore(List<Square> squares) {
        double sum = 0;
        List<Square> notPawnList = squares.stream()
                .filter(square -> !square.isPawn())
                .collect(Collectors.toList());

        for (final Square square : notPawnList) {
            sum += square.getScore();
        }
        squares.removeAll(notPawnList);
        sum += sumOfPawn(squares);
        return sum;
    }


    private double sumOfPawn(List<Square> pawns) {
        double sum = 0;
        for (int i = 'a'; i <= 'h'; i++) {
            sum += sumOfPawn(i, pawns);
        }
        return sum;
    }

    private double sumOfPawn(int column, List<Square> pawns) {
        List<Square> test = pawns.stream()
                .filter(square -> square.isSameColumn(Column.from(String.valueOf((char) column))))
                .filter(Square::isPawn)
                .collect(Collectors.toList());
        if (test.size() >= 2) {
            return test.size() * 0.5;
        }
        return test.size();
    }
}

