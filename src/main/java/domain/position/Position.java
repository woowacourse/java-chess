package domain.position;

public record Position(File file, Rank rank) {
    public int rowIndex() {
        return rank.getIndex();
    }

    public int columnIndex() {
        return file.getIndex();
    }

    public Position add(final UnitVector unitVector) {
        File newFile = File.of(columnIndex() + unitVector.getCol());
        Rank newRank = Rank.of(rowIndex() + unitVector.getRow());

        return new Position(newFile, newRank);
    }
}
