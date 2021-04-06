package chess.domain.position;

import chess.exception.DomainException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Position {
    public static final int POSITION_FORMAT_LENGTH = 2;
    public static final int FILE_INDEX = 0;
    public static final int RANK_INDEX = 1;
    public static final Pattern POSITION_PATTERN = Pattern.compile("([a-h][1-8])");

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return new Position(file, rank);
    }

    public static Position of(String position) {
        List<String> splitPosition = Arrays.asList(position.split(""));

        if (splitPosition.size() != POSITION_FORMAT_LENGTH || !POSITION_PATTERN.matcher(position).find()) {
            throw new DomainException("위치 형식에 맞는 입력이 아닙니다.");
        }
        File file = File.from(splitPosition.get(FILE_INDEX));
        Rank rank = Rank.from(splitPosition.get(RANK_INDEX));

        return new Position(file, rank);
    }

    public int calculateXDegree(Position that) {
        return this.file.calculateGapAsInt(that.file);
    }

    public int calculateYDegree(Position that) {
        return this.rank.calculateGapAsInt(that.rank);
    }

    public Position getNextPosition(Direction direction) {
        return Position.of(this.file.add(direction.getXDegree()), this.rank.add(direction.getYDegree()));
    }

    public boolean isRankOf(Rank rank) {
        return this.rank == rank;
    }

    public String getNotation() {
        return this.file.getLetter() + rank.getLetter();
    }

    @Override
    public String toString() {
        return file.getLetter() + rank.getLetter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
