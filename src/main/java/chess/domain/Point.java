package chess.domain;

import chess.view.InputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point {
    private static final int ASCII_CODE_GAP = 97;
    private static final int RANK_GAP = 8;
    private static final char MINIMUM_LETTER = 'a';
    private static final char MAXIMUM_LETTER = 'h';
    private static final int MINIMUM_RANK = 1;
    private static final int MAXIMUM_RANK = 8;

    private static final List<Point> points = new ArrayList<>();

    static {
        for (int i = MINIMUM_LETTER; i <= MAXIMUM_LETTER; i++) {
            for (int j = MAXIMUM_RANK; MINIMUM_RANK <= j; j--) {
                points.add(new Point((char) i, j));
            }
        }
    }

    private final int x;
    private final int y;

    private Point(char letter, int rank) {
        x = convertLetterToIndex(letter);
        y = convertRankToIndex(rank);
    }

    public static Point of(char letter, int rank) {
        return points.stream()
                .filter(p -> p.x == p.convertLetterToIndex(letter) && p.y == p.convertRankToIndex(rank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(InputView.INVALID_INPUT_ERROR_MESSAGE));
    }

    public static Point valueOf(int row, int column) {
        return points.stream()
                .filter(p -> p.x == column && p.y == row)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(InputView.INVALID_INPUT_ERROR_MESSAGE));
    }

    private int convertLetterToIndex(char letter) {
        return letter - ASCII_CODE_GAP;
    }

    private int convertRankToIndex(int rank) {
        return RANK_GAP - rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
