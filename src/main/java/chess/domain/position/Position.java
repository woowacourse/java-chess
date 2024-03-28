package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position fromCoordinate(Coordinate coordinate) {
        return new Position(coordinate.file(), coordinate.rank());
    }

    public PositionDifference calculateDifferenceTo(Position position) {
        return new PositionDifference(
                file.calculateDifference(position.file),
                rank.calculateDifference(position.rank)
        );
    }

    public List<Position> findRoute(Position position) {
        List<File> files = file.getFileRoute(position.file);
        List<Rank> ranks = rank.getRankRoute(position.rank);

        if (files.size() == ranks.size()) {
            return findDiagonalRoute(files, ranks);
        }
        return findStraightRoute(files, ranks);
    }

    private List<Position> findDiagonalRoute(List<File> files, List<Rank> ranks) {
        List<Position> positions = new ArrayList<>();
        if (files.size() != ranks.size()) {
            throw new IllegalArgumentException("이동할 수 없는 경로가 입력되었습니다.");
        }
        for (int i = 0; i < files.size(); i++) {
            positions.add(new Position(
                    files.get(i), ranks.get(i)
            ));
        }

        return positions;
    }

    private List<Position> findStraightRoute(List<File> files, List<Rank> ranks) {
        return files.stream()
                .flatMap(file -> ranks.stream()
                        .map(rank -> new Position(file, rank)))
                .toList();
    }

    public boolean isSameFile(File otherFile) {
        return file == otherFile;
    }

    public boolean isSameRank(Rank otherRank) {
        return rank == otherRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;

        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
