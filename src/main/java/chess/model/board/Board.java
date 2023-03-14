package chess.model.board;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import java.util.stream.Stream;

public class Board {

    private final List<Square> squares;

    private Board(final List<Square> squares) {
        this.squares = squares;
    }

    public static Board create() {
        List<Square> squares = stream(File.values())
            .flatMap(Board::createSquare)
            .collect(toUnmodifiableList());

        return new Board(squares);
    }

    private static Stream<Square> createSquare(final File file) {
        return stream(Rank.values())
            .map(rank -> new Square(rank, file));
    }
}
