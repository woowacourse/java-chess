package domain.position;

public record Position(File file, Rank rank) {

    public Position next(final CommonMovementDirection commonMovementDirection) {
        File nextFile = File.of(this.columnIndex() + commonMovementDirection.getColumnDistance());
        Rank nextRank = Rank.of(this.rowIndex() + commonMovementDirection.getRowDistance());

        return new Position(nextFile, nextRank);
    }

    public int rowIndex() {
        return rank.getIndex();
    }

    public int columnIndex() {
        return file.getIndex();
    }
}
