package chess.domain.position;

import chess.domain.Direction;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
    private static final Map<String, Position> POOL = new HashMap<>();

    private final File file;
    private final Rank rank;

    static {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                POOL.put(toKey(file, rank), new Position(file, rank));
            }
        }
    }

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position convert(String source) {
        return POOL.computeIfAbsent(source, ignore -> {
            throw new IllegalArgumentException("존재하지 않는 위치입니다.");
        });
    }

    public static Position of(File file, Rank rank) {
        return POOL.get(toKey(file, rank));
    }

    public boolean canMoveNext(Direction direction) {
        int nextFile = this.file.getValue() + direction.getX();
        int nextRank = this.rank.getValue() + direction.getY();

        return file.isInRange(nextFile) && rank.isInRange(nextRank);
    }

    public Position next(Direction direction) {
        int x = direction.getX();
        int y = direction.getY();

        return Position.of(file.add(x), rank.add(y));
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
