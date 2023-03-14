package chess;

public class Position {
    private final Rank rank;
    private final File file;

    Position(String rawRank, String rawFile) {
        this.file = File.from(rawFile);
        this.rank = Rank.from(rawRank);
    }

    int calculateFileDistance(final Position startPosition) {
        return file.calculateDistance(startPosition.file);
    }

    int calculateRankDistance(final Position startPosition) {
        return rank.calculateDistance(startPosition.rank);
    }
}
