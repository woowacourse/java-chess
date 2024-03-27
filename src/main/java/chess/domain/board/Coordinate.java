package chess.domain.board;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Coordinate {

    final static Map<String, Coordinate> COORDINATE_POOL;

    static {
        List<File> files = Arrays.stream(File.values()).toList();
        List<Rank> ranks = Arrays.stream(Rank.values()).toList();

        COORDINATE_POOL = files.stream()
                .flatMap(file -> ranks.stream()
                        .map(rank -> new Coordinate(file, rank))
                )
                .collect(Collectors.toMap(
                        coordinate -> toKey(coordinate.file, coordinate.rank),
                        Function.identity()
                ));
    }

    private final File file;
    private final Rank rank;

    private Coordinate(final File file, final Rank rank) {
        this.rank = rank;
        this.file = file;
    }

    public static Coordinate of(File file, Rank rank) {
        if (COORDINATE_POOL.get(toKey(file, rank)) == null) {
            throw new IllegalArgumentException("존재하지 않는 좌표 입니다.");
        }
        return COORDINATE_POOL.get(toKey(file, rank));
    }

    private static String toKey(File file, Rank rank) {
        return file.name() + rank.name();

    }

    public Coordinate move(int fileWeight, int rankWeight) {
        if (!canMove(fileWeight, rankWeight)) {
            throw new IllegalArgumentException("해당 좌표로 움직일 수 없습니다.");
        }
        return Coordinate.of(file.move(fileWeight), rank.move(rankWeight));
    }

    public boolean canMove(int fileWeight, int rankWeight) {
        return file.canMove(fileWeight) && rank.canMove(rankWeight);
    }

    public Entry<Integer, Integer> compare(Coordinate other) {
        return Map.entry(this.file.compare(other.file), this.rank.compare(other.rank));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return Objects.equals(rank, that.rank) && Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "rank=" + rank +
                ", file=" + file +
                '}';
    }
}
