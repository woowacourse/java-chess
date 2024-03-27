package domain.board.position;

import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(final String command) {
        if (command.length() != 2) {
            throw new IllegalArgumentException(String.format("입력된 명령어: %s, 명령어는 파일, 랭크로 구성되어 있어야 합니다", command));
        }
        final File file = File.from(command.substring(0, 1));
        final Rank rank = Rank.from(command.substring(1, 2));
        return new Position(file, rank);
    }

    public Position next(final int fileDelta, final int rankDelta) {
        return new Position(File.of(file.toIndex() + fileDelta), Rank.of(rank.toIndex() + rankDelta));
    }

    public int toFileIndex() {
        return file.toIndex();
    }

    public int toRankIndex() {
        return rank.toIndex();
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
