package domain.board;

import domain.piece.MovementDirection;

public record Position(File file, Rank rank) {
    private static final int POSITIONS_VALUES_SIZE = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    public static Position of(final String value) {
        String[] positionValues = value.split("");
        validatePositionValue(positionValues);

        File file = File.of(positionValues[FILE_INDEX]);
        Rank rank = Rank.of(positionValues[RANK_INDEX]);

        return new Position(file, rank);
    }

    private static void validatePositionValue(final String[] values) {
        if (values.length != POSITIONS_VALUES_SIZE) {
            throw new IllegalArgumentException("잘못된 위치값입니다.");
        }
    }

    public Position next(final MovementDirection movementDirection) {
        File nextFile = File.of(this.columnIndex() + movementDirection.getColumnDistance());
        Rank nextRank = Rank.of(this.rowIndex() + movementDirection.getRowDistance());

        return new Position(nextFile, nextRank);
    }

    public int rowIndex() {
        return rank.getIndex();
    }

    public int columnIndex() {
        return file.getIndex();
    }
}
