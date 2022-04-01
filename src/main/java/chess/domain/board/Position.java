package chess.domain.board;

import java.util.Objects;

public final class Position {
    private static final String POSITION_INPUT_DELIMITER = "";
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(String input) {
        final String[] splitInput = input.trim().split(POSITION_INPUT_DELIMITER);
        return new Position(File.from(splitInput[FILE_INDEX]), Rank.from(splitInput[RANK_INDEX]));
    }

    public static Position of(File file, Rank rank) {
        return new Position(file, rank);
    }

    public int dx(Position another) {
        return file.dx(another.file);
    }

    public int dy(Position another) {
        return rank.dy(another.rank);
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
