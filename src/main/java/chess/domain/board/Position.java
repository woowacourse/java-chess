package chess.domain.board;

import java.util.Objects;

public final class Position {

    private static final int NEAR_SQUARES = 1;
    private static final int TWO_SQUARES = 2;
    private static final int EQUAL_FILE = 0;

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isBlackPawnInitRank() {
        return rank == Rank.SEVEN;
    }

    public boolean isWhitePawnInitRank() {
        return rank == Rank.TWO;
    }

    public Position getUpStraight() {
        return new Position(file, rank.plus());
    }

    public Position getRightDownDiagonal() {
        return new Position(file.plus(), rank.minus());
    }

    public Position getDownStraight() {
        return new Position(file, rank.minus());
    }

    public Position getLeftDownDiagonal() {
        return new Position(file.minus(), rank.minus());
    }

    public Position getLeftStraight() {
        return new Position(file.minus(), rank);
    }

    public boolean isFileEquals(final Position target) {
        return file.equals(target.file);
    }

    public boolean isRankEquals(final Position target) {
        return rank.equals(target.rank);
    }

    public boolean isFileOver(final Position target) {
        return file.isOver(target.file);
    }

    public boolean isRankOver(final Position target) {
        return rank.isOver(target.rank);
    }

    public static Position maxRank(final Position source, final Position target) {
        if (source.isRankOver(target)) {
            return source;
        }
        return target;
    }

    public static Position minRank(final Position source, final Position target) {
        if (source.isRankOver(target)) {
            return target;
        }
        return source;
    }

    public static Position maxFile(final Position source, final Position target) {
        if (source.isFileOver(target)) {
            return source;
        }
        return target;
    }

    public static Position minFile(final Position source, final Position target) {
        if (source.isFileOver(target)) {
            return target;
        }
        return source;
    }

    public double computeInclination(final Position target) {
        final var fileSub = target.file.sub(this.file);
        final var rankSub = target.rank.sub(this.rank);

        return fileSub / (double) rankSub;
    }

    public boolean isNear(final Position target) {
        return Math.abs(file.sub(target.file)) <= NEAR_SQUARES && Math.abs(rank.sub(target.rank)) <= NEAR_SQUARES;
    }

    public boolean canKnightJump(final Position target) {
        final var fileSub = Math.abs(this.file.sub(target.file));
        final var rankSub = Math.abs(this.rank.sub(target.rank));

        return (fileSub == TWO_SQUARES && rankSub == NEAR_SQUARES) || (fileSub == NEAR_SQUARES && rankSub == TWO_SQUARES);
    }

    public boolean canWhitePawnMove(final Position target) {
        int fileSub = target.file.sub(file);
        int rankSub = target.rank.sub(rank);
        return (Math.abs(fileSub) <= NEAR_SQUARES && rankSub == NEAR_SQUARES) ||
                (fileSub == EQUAL_FILE && rankSub == TWO_SQUARES);
    }

    public boolean canBlackPawnMove(final Position target) {
        int fileSub = file.sub(target.file);
        int rankSub = rank.sub(target.rank);
        return (Math.abs(fileSub) <= NEAR_SQUARES && rankSub == NEAR_SQUARES) ||
                (fileSub == EQUAL_FILE && rankSub == TWO_SQUARES);
    }

    public int getRank() {
        return rank.getValue() - NEAR_SQUARES;
    }

    public int getFile() {
        return file.getValue() - NEAR_SQUARES;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
                "rank=" + file +
                ", file=" + rank +
                '}';
    }
}
