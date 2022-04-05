package chess.domain.position;

import java.util.Objects;

public class Position {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int POSITION_LENGTH = 2;

    private final File file;
    private final Rank rank;

    public Position(int fileIndex, int rankIndex) {
        this.file = File.of(fileIndex);
        this.rank = Rank.of(rankIndex);
    }

    public Position(String position) {
        validateLength(position);
        this.file = File.of(position.substring(FILE_INDEX, RANK_INDEX));
        this.rank = Rank.of(position.substring(RANK_INDEX));
    }

    private void validateLength(String position) {
        if (position.length() != POSITION_LENGTH) {
            throw new IllegalArgumentException("위치는 두자리여야 합니다.");
        }
    }

    public Position add(Direction direction) {
        int fileIndex = file.getIndex() + direction.getCol();
        int rankIndex = rank.getIndex() + direction.getRow();
        return new Position(fileIndex, rankIndex);
    }

    public String getNotation() {
        return file.getNotation() + rank.getNotation();
    }

    public int getFileIndex() {
        return file.getIndex();
    }

    public int getRankIndex() {
        return rank.getIndex();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
