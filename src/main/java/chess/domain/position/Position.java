package chess.domain.position;

import java.util.Objects;

public class Position {
    private Rank rank;
    private File file;

    public Position(String text) {
        validate(text);
        this.rank = Rank.of(String.valueOf(text.charAt(0)));
        this.file = File.of(Character.getNumericValue(text.charAt(1)));
    }

    private void validate(String text) {
        if (text.length() != 2) {
            throw new IllegalArgumentException("위치는 가로줄과 세로줄의 정보를 전부 포함해야합니다.");
        }
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
        return file.equals(File.of(Integer.parseInt(target)));
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
        return toGap(rankDistance(target));
    }

    private int calculateFileGap(Position target) {
        return toGap(fileDistance(target));
    }

    private int toGap(int distance) {
        if (distance > 0) {
            distance = 1;
        }

        if (distance < 0) {
            distance = -1;
        }
        return distance;
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
