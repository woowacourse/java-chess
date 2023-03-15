package chess.board;

public class Position {

    private File file;
    private Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public void move(final int fileMovingCount, final int rankMovingCount) {
        this.file = file.getAddedFile(fileMovingCount);
        this.rank = rank.getAddedRank(rankMovingCount);
    }

    public int getFile() {
        return file.getValue();
    }

    public int getRank() {
        return rank.getValue();
    }
}
