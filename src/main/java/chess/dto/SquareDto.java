package chess.dto;

import java.util.regex.Pattern;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

public class SquareDto {

    private static final String SQUARE_REGEX = "^[a-h][1-8]$";

    private final Square square;

    private SquareDto(final Square square) {
        this.square = square;
    }

    public static SquareDto of(final String square) {
        validateSquare(square);
        char file = square.charAt(0);
        char rank = square.charAt(1);
        return new SquareDto(Square.of(File.from(file), Rank.from(rank)));
    }

    private static void validateSquare(final String square) {
        if (Pattern.matches(SQUARE_REGEX, square)) {
            return;
        }
        throw new IllegalArgumentException("잘못된 보드 위치입니다. 보드는 a~h, 1~8로 구성됩니다.");
    }

    public Square getSquare() {
        return square;
    }
}
