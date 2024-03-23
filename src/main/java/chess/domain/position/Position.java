package chess.domain.position;

import chess.domain.Direction;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Position {
    private static final Map<String, Position> POOL;

    static {
        POOL = Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> new Position(file, rank)))
                .collect(Collectors.toMap(position -> toKey(position.file, position.rank), Function.identity()));
    }

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(String source) {
        if (POOL.containsKey(source)) {
            return POOL.get(source);
        }

        throw new IllegalArgumentException("move source위치 target위치가 잘못됐습니다. - 예. move b2 b3");
    }

    public static Position from(File file, Rank rank) {
        return from(toKey(file, rank));
    }

    public boolean canMoveNext(Direction direction) {
        int nextFile = this.file.getValue() + direction.getX();
        int nextRank = this.rank.getValue() + direction.getY();

        return file.isInRange(nextFile) && rank.isInRange(nextRank);
    }

    public Position next(Direction direction) {
        int x = direction.getX();
        int y = direction.getY();

        return new Position(file.add(x), rank.add(y));
    }

    private static String toKey(File file, Rank rank) {
        return file.getCommand() + rank.getCommand();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position position)) {
            return false;
        }
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }

    public int getFile() {
        return file.getValue();
    }

    public int getRank() {
        return rank.getValue();
    }
}
