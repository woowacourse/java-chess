package chess.domain.position;

import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(String file, String rank) {
        this(File.from(file), Rank.from(rank));
    }

    public Position move(Direction direction) {
        File fileUpdate = file.update(direction.getFileDirection());
        Rank rankUpdate = rank.update(direction.getRankDirection());
        return new Position(fileUpdate, rankUpdate);
    }

    public int calculateFileDistance(Position target) {
        return Math.abs(file.subtractFile(target.file));
    }

    public int calculateRankDistance(Position target) {
        return Math.abs(subtractRanks(target));
    }

    public int subtractRanks(Position target) {
        return rank.subtractRank(target.rank);
    }

    public boolean isSameRank(Position target) {
        return rank == target.rank;
    }

    public boolean isSameFile(Position target) {
        return file == target.file;
    }

    public int findFileDirection(Position target) {
        return file.findDirection(target.file);
    }

    public int findRankDirection(Position target) {
        return rank.findDirection(target.rank);
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Position position = (Position) object;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
