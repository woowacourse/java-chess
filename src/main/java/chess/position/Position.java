package chess.position;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public PositionDifference calculateDifferenceTo(Position position) {
        return new PositionDifference(
                file.calculateDifference(position.file),
                rank.calculateDifference(position.rank)
        );
    }
}
