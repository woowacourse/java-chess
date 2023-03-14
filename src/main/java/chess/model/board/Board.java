package chess.model.board;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Square> squares;

    private Board(final List<Square> squares) {
        this.squares = squares;
    }

    public static Board create() {
        final List<Square> squares = createSquares();

        return new Board(squares);
    }

    private static List<Square> createSquares() {
        final List<Square> squares = new ArrayList<>();

        for (File file : File.values()) {
            createSquare(squares, file);
        }

        return squares;
    }

    private static void createSquare(final List<Square> squares, final File file) {
        for (Rank rank : Rank.values()) {
            final Position position = new Position(file, rank);
            squares.add(new EmptySquare(position));
        }
    }

    public List<Square> getSquares() {
        return List.copyOf(squares);
    }
}
