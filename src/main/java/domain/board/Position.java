package domain.board;

import domain.piece.MovementDirection;

public record Position(File file, Rank rank) {

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
