package chess.domain.position;

import java.util.Objects;

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

    public Position move(Move move) {
        File newFile = file.move(move.getDeltaFile());
        Rank newRank = rank.move(move.getDeltaRank());
        return new Position(newFile, newRank);
    }

    public static int getDeltaFile(Position source, Position target) {
        return target.getFileIndex() - source.getFileIndex();
    }

    public static int getDeltaRank(Position source, Position target) {
        return target.getRankIndex() - source.getRankIndex();
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
