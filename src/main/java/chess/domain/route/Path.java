package chess.domain.route;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Path {

    private final List<Square> squares;

    public Path() {
        this.squares = new ArrayList<>();
    }

    public Path(List<Square> squares) {
        this.squares = new ArrayList<>(squares);
    }

    public static Path of(Square source, Square target) {
        if (source.isDiagonal(target)) {
            return new Path(makeDiagonalPath(source, target));
        }
        if (source.isSameFile(target)) {
            return new Path(makeVerticalPath(source, target));
        }
        if (source.isSameRank(target)) {
            return new Path(makeHorizontalPath(source, target));
        }
        return new Path();
    }

    private static List<Square> makeDiagonalPath(Square source, Square target) {
        List<File> files = source.findBetweenFiles(target);
        List<Rank> ranks = source.findBetweenRanks(target);

        return IntStream.range(0, files.size())
                .mapToObj(i -> new Square(files.get(i), ranks.get(i)))
                .toList();
    }

    private static List<Square> makeHorizontalPath(Square source, Square target) {
        List<File> files = source.findBetweenFiles(target);

        return files.stream()
                .map(source::createWithSameRank)
                .toList();
    }

    private static List<Square> makeVerticalPath(Square source, Square target) {
        List<Rank> ranks = source.findBetweenRanks(target);

        return ranks.stream()
                .map(source::createWithSameFile)
                .toList();
    }

    public boolean contains(Square square) {
        return squares.contains(square);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Path path = (Path) o;
        return Objects.equals(squares, path.squares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares);
    }
}
