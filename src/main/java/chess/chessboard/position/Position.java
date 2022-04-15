package chess.chessboard.position;

import java.util.Objects;

public final class Position {

    private static final int RANK_NAME_INDEX = 1;
    private static final int FILE_NAME_INDEX = 0;
    private final Rank rank;
    private final File file;

    private Position(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position of(final Rank rank, final File file) {
        return new Position(rank, file);
    }

    public static Position from(final String position) {
        return new Position(Rank.of(position.charAt(RANK_NAME_INDEX)), File.of(position.charAt(FILE_NAME_INDEX)));
    }

    public boolean isInBoardAfterMoved(final Direction direction) {
        return direction.isMovablePosition(rank, file);
    }

    public Position createMovablePosition(final Direction direction) {
        return direction.createMovablePosition(rank, file);
    }

    public String getPosition() {
        return String.valueOf(file.getName()) + rank.getName();
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

    public boolean isStartingPositionOfPawn() {
        return rank.equals(Rank.TWO) || rank.equals(Rank.SEVEN);
    }
}
