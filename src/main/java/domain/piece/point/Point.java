package domain.piece.point;

public record Point(File file, Rank rank) {

    public int getFileIndex() {
        return this.file.ordinal();
    }

    public int getRankIndex() {
        return this.rank.ordinal();
    }
}
