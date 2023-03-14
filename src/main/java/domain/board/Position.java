package domain.board;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Position {
    private static final int ROW_POSITION = 0;
    private static final int COLUMN_POSITION = 1;
    private static final int VALID_POSITION_LENGTH = 2;
    private static final String INVALID_POSITION = "좌표는 2글자입니다.";

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(String position) {
        validatePosition(position);

        final String row = String.valueOf(position.charAt(ROW_POSITION));
        final String column = String.valueOf(position.charAt(COLUMN_POSITION));

        return new Position(File.from(row), Rank.from(column));
    }

    private static void validatePosition(String position) {
        if (position.length() != VALID_POSITION_LENGTH) {
            throw new IllegalArgumentException(INVALID_POSITION);
        }
    }

    public static List<Position> of(String... positions) {
        return Arrays.stream(positions)
                .map(Position::from)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
