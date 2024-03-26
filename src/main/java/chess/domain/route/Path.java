package chess.domain.route;

import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Path {

    private final List<Position> positions;

    public Path() {
        this.positions = new ArrayList<>();
    }

    public Path(List<Position> positions) {
        this.positions = new ArrayList<>(positions);
    }

    public static Path of(Position source, Position target) {
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

    private static List<Position> makeDiagonalPath(Position source, Position target) {
        List<File> files = source.findBetweenFiles(target);
        List<Rank> ranks = source.findBetweenRanks(target);

        return IntStream.range(0, files.size())
                .mapToObj(i -> new Position(files.get(i), ranks.get(i)))
                .toList();
    }

    private static List<Position> makeHorizontalPath(Position source, Position target) {
        List<File> files = source.findBetweenFiles(target);

        return files.stream()
                .map(source::createWithSameRank)
                .toList();
    }

    private static List<Position> makeVerticalPath(Position source, Position target) {
        List<Rank> ranks = source.findBetweenRanks(target);

        return ranks.stream()
                .map(source::createWithSameFile)
                .toList();
    }

    public boolean contains(Position position) {
        return positions.contains(position);
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
        return Objects.equals(positions, path.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions);
    }
}
