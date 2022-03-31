package chess.domain.board;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(String position) {
        String[] attribute = position.split("");
        if (attribute.length != 2) {
            throw new IllegalArgumentException("잘못된 위치 값 입니다.");
        }
        File file = File.letterOf(attribute[0]);
        Rank rank = Rank.letterOf(attribute[1]);

        return new Position(file, rank);
    }

    public static Position of(String column, String row) {
        return new Position(File.letterOf(column), Rank.letterOf(row));
    }

    public Position move(int column, int row) {
        return new Position(file.plus(column), rank.plus(row));
    }

    public boolean isMovable(int column, int row) {
        return isFileInRange(column) && isRankInRange(row);
    }

    private boolean isFileInRange(int value) {
        return file.isMoveInRange(file.getNumber() + value);
    }

    private boolean isRankInRange(int value) {
        return rank.isMoveInRange(rank.getNumber() + value);
    }

    public boolean isInitLine() {
        return rank == Rank.TWO || rank == Rank.SEVEN;
    }

    public boolean isEqualsColumn(Position otherPosition) {
        return this.file == otherPosition.file || this.rank == otherPosition.rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
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
