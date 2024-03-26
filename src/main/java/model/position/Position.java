package model.position;

import constant.ErrorCode;
import exception.InvalidPositionException;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(final String command) {
        validate(command);
        return new Position(File.from(command.charAt(0)), Rank.from(command.charAt(1)));
    }

    private static void validate(final String command) {
        if (command == null || command.isBlank()) {
            throw new InvalidPositionException(ErrorCode.INVALID_POSITION);
        }
        if (command.length() != 2) {
            throw new InvalidPositionException(ErrorCode.INVALID_POSITION);
        }
    }

    public int getRankIndex() {
        return rank.getIndex();
    }

    public int getFileIndex() {
        return file.getIndex();
    }

    public File getFile() {
        return file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Position position)) {
            return false;
        }
        return Objects.equals(rank, position.rank) && Objects.equals(file, position.file);
    }

    @Override
    public String toString() {
        return file.getValue() + rank.getValue();
    }
}
