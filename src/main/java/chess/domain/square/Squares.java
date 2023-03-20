package chess.domain.square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Squares {
    private static final List<Square> squares = new ArrayList<>();

    static {
        createSquares();
    }

    private Squares() {
    }

    private static void createSquares() {
        Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> Square.of(file, rank)))
                .forEach(squares::add);
    }

    public static Square getSquare(final File file, final Rank rank) {
        return squares.stream().filter(square -> square.getFile() == file)
                .filter(square -> square.getRank() == rank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 위치가 아닙니다."));
    }

    public static int getIndex(final Square square) {
        return squares.indexOf(square);
    }
}
