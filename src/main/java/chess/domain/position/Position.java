package chess.domain.position;

import java.util.Objects;

public class Position {
    private Rank rank;
    private File file;

    public Position(String text) {
        this.rank = Rank.of(String.valueOf(text.charAt(0)));
        this.file = File.of(String.valueOf(text.charAt(1)));
    }

    public int fileDistance(Position target) {
        return file.calculateDistance(target.file);
    }

    public int rankDistance(Position target) {
        return rank.calculateDistance(target.rank);
    }

    public boolean isSameFile(Position target) {
        return file.equals(target.file);
    }

    public boolean isSameFile(String target) {
        return file.equals(File.of(target));
    }


    public boolean isSameRank(Position target) {
        return rank.equals(target.rank);
    }

    public Direction findDirection(Position target) {
        int rankDistance = calculateRankGap(target);
        int fileDistance = calculateFileGap(target);

        return Direction.of(rankDistance, fileDistance);
    }

    private int calculateRankGap(Position target) {
        int rankDistance = rankDistance(target);
        if (rankDistance > 0) {
            rankDistance = 1;
        }

        if (rankDistance < 0) {
            rankDistance = -1;
        }
        return rankDistance;
    }

    private int calculateFileGap(Position target) {
        int fileDistance = fileDistance(target);
        if (fileDistance > 0) {
            fileDistance = 1;
        }

        if (fileDistance < 0) {
            fileDistance = -1;
        }
        return fileDistance;
    }

    public Position toNextPosition(Direction direction) {
        this.file = file.add(direction.fileGap());
        this.rank = rank.add(direction.rankGap());
        return new Position(rank.getValue() + file.getValue());
    }

    public String getValue() {
        return rank.getValue() + file.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
