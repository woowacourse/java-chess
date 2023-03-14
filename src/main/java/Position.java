public final class Position {
    private final Rank rank;
    private final File file;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(String fileText, String rankText) {
        File file = File.from(fileText);
        Rank rank = Rank.from(rankText);
        return new Position(file, rank);
    }

    public Movement calculateIncrement(Position targetPosition) {
        int fileIncrement = this.file.calculateIncrement(targetPosition.file);
        int rankIncrement = this.rank.calculateIncrement(targetPosition.rank);
        return new Movement(fileIncrement, rankIncrement);
    }
}
