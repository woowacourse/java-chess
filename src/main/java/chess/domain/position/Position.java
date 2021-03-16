package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return new Position(file, rank);
    }

    public static Position of(String position) {
        List<String> splitPosition = Arrays.asList(position.split(""));

        // TODO 검증 로직 및 상수화 리팩토링
        File file = File.from(splitPosition.get(0));
        Rank rank = Rank.from(splitPosition.get(1));

        return new Position(file, rank);
    }

    @Override
    public String toString() {
        return file.getLetter() + rank.getLetter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
