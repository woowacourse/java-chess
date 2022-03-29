package chess.domain.board.position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Position {

    private static final String NO_POSITION_FORMAT_ERROR_MESSAGE = "위치 값의 길이는 2 여야 합니다";
    private static final int POSITION_BEGIN_INDEX = 5;
    private static final String SEPARATOR = " ";
    private static final int ATTRIBUTE_SIZE = 2;

    private final Column column;
    private final Rank rank;

    public Position(Column column, Rank rank) {
        this.column = column;
        this.rank = rank;
    }

    public static Position from(String position) {
        String[] attribute = position.split("");
        if (attribute.length != ATTRIBUTE_SIZE) {
            throw new IllegalArgumentException(NO_POSITION_FORMAT_ERROR_MESSAGE);
        }
        Column column = Column.letterOf(attribute[0]);
        Rank rank = Rank.conditionOf(attribute[1]);

        return new Position(column, rank);
    }

    public static List<Position> inputToPositions(String input) {
        return Arrays.stream(input.substring(POSITION_BEGIN_INDEX)
                        .split(SEPARATOR))
                .map(Position::from)
                .collect(Collectors.toList());
    }

    public Position advancePosition(Direction direction) {
        return new Position(
                Column.numberOf(column.getNumber() + direction.getX()),
                Rank.numberOf(rank.getNumber() + direction.getY())
        );
    }

    public int getXDistance(Position to) {
        return to.column.getNumber() - this.column.getNumber();
    }

    public int getYDistance(Position to) {
        return to.rank.getNumber() - this.rank.getNumber();
    }

    public boolean isEqualRank(Rank rank) {
        return this.rank == rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(column, position.column) && Objects.equals(rank, position.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + column +
                ", rank=" + rank +
                '}';
    }
}
