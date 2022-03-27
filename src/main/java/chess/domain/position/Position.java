package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final File file;
    private final Rank rank;

    private static final List<Position> TOTAL = new ArrayList<>();

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        Position newPosition = new Position(file, rank);
        return TOTAL.stream()
                .filter(position -> position.equals(newPosition))
                .findFirst()
                .orElse(newPosition);
    }

    public static Position from(final String position) {
        File file = File.from(position.substring(FILE_INDEX, RANK_INDEX));
        Rank rank = Rank.from(Integer.parseInt(position.substring(RANK_INDEX)));

        return Position.of(file, rank);
    }

    public File getFile() {
        return file;
    }

    public int getFileOrder() {
        return file.getOrder();
    }

    public int getRank() {
        return rank.getRank();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Position)) return false;

        Position position = (Position) other;

        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
