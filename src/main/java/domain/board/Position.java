package domain.board;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(int file, int rank) {
        return new Position(new File(file), new Rank(rank));
    }

    public Position nextPosition(Direction direction) {
        return new Position(new File(file.value() + direction.getFileDirection()),
            new Rank(rank.value() + direction.getRankDirection()));
    }

    public boolean isWhitePawnInitialPosition() {
        return rank.isRankTwo();
    }

    public boolean isBlackPawnInitialPosition() {
        return rank.isRankSeven();
    }

    public int calculateFileDifference(Position otherPosition) {
        return file.subtract(otherPosition.file);
    }

    public int calculateRankDifference(Position otherPosition) {
        return rank.subtract(otherPosition.rank);
    }

    // TODO: isOnSameDiagonal, smaeLine 이런거 만들어서 더  메소드에 의미 부여하기
    // TODO: 정팩메 만들고  -- 나중에 시간 나면 캐싱

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(file, position.file) && Objects.equals(rank, position.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
            "file=" + file +
            ", rank=" + rank +
            '}';
    }
}
