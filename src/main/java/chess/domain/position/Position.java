package chess.domain.position;

import chess.domain.Direction;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Position {
    private static final int FILE_RANK_COUNT = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position convert(String source) {
        List<String> parts = Arrays.stream(source.split("")).toList();

        if (parts.size() != FILE_RANK_COUNT) {
            throw new IllegalArgumentException("move source위치 target위치를 정확히 입력해 주세요. - 예. move b2 b3");
        }

        File file = File.from(parts.get(FILE_INDEX));
        Rank rank = Rank.from(parts.get(RANK_INDEX));

        return new Position(file, rank);
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
