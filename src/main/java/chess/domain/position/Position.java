package chess.domain.position;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Position {

    protected static final String INVALID_POSITION = "잘못된 포지션입니다.";

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(String position) {
        position = position.toUpperCase();
        validate(position);
        String fileName = Character.toString(position.charAt(0));
        int rankIndex = position.charAt(1) - '0';
        this.file = File.from(fileName);
        this.rank = Rank.from(rankIndex);
    }

    private void validate(String position) {
        validateLength(position);
        validateForm(position);
    }

    private void validateLength(String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException(INVALID_POSITION);
        }
    }

    private void validateForm(String parameter) {
        if (!parameter.matches("[A-Z]\\d")) {
            throw new IllegalArgumentException(INVALID_POSITION);
        }
    }

    public Set<Position> findRoute(Position other) {
        if (!(isStraight(other) || isDiagonal(other))) {
            return Collections.emptySet();
        }
        int unit = findUnit(other);
        Set<Position> route = new HashSet<>();
        for (int i = 1; i < unit; i++) {
            route.add(calculateEachRoute(unit, i, other));
        }
        return route;
    }

    private int findUnit(Position other) {
        int deltaFile = getDeltaFile(other);
        int deltaRank = getDeltaRank(other);
        return Math.max(Math.abs(deltaFile), Math.abs(deltaRank));
    }

    private Position calculateEachRoute(int unit, int index, Position other) {
        int deltaFile = getDeltaFile(other) / unit * index;
        int deltaRank = getDeltaRank(other) / unit * index;
        return move(deltaFile, deltaRank);
    }

    private Position move(int deltaFile, int deltaRank) {
        File newFile = file.move(deltaFile);
        Rank newRank = rank.move(deltaRank);
        return new Position(newFile, newRank);
    }

    public boolean isStraight(Position other) {
        return getDeltaFile(other) * getDeltaRank(other) == 0;
    }

    public boolean isDiagonal(Position other) {
        return Math.abs(getDeltaFile(other)) == Math.abs(getDeltaRank(other));
    }

    public int getDeltaFile(Position other) {
        return other.getFileIndex() - getFileIndex();
    }

    public int getDeltaRank(Position other) {
        return other.getRankIndex() - getRankIndex();
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
