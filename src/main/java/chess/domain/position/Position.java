package chess.domain.position;

import chess.domain.path.Movement;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<String, Position> CACHE = new HashMap<>();

    static {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                CACHE.put(file.command() + rank.command(), new Position(file, rank));
            }
        }
    }

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(final String file, final String rank) {
        this(File.from(file), Rank.from(rank));
    }

    public static Position of(final File file, final Rank rank) {
        return CACHE.get(file.command() + rank.command());
    }

    public static Position of(final int file, final int rank) {
        return of(File.from(file), Rank.from(rank));
    }

    public Movement convertMovement(Position from) {
        int rankGap = this.rankGap(from);
        int fileGap = this.fileGap(from);

        int greatestCommonDivisor = findGreatestCommonDivisor(Math.max(rankGap, fileGap),
                Math.min(rankGap, fileGap));
        validateGreatestCommonDivisor(greatestCommonDivisor);

        return Movement.of(fileGap / greatestCommonDivisor,
                rankGap / greatestCommonDivisor);
    }

    private static void validateGreatestCommonDivisor(final int greatestCommonDivisor) {
        if (greatestCommonDivisor == 0) {
            throw new IllegalStateException("출발지와 목적지가 동일해 이동할 수 없음!");
        }
    }

    private int findGreatestCommonDivisor(int number1, int number2) {
        if (number2 == 0) {
            return Math.abs(number1);
        }
        return findGreatestCommonDivisor(number2, number1 % number2);
    }

    public Position moveBy(Movement movement) {
        return movement.nextPosition(file, rank);
    }

    public boolean isSameRank(int rank) {
        return this.rank.isSame(rank);
    }

    public int rankGap(final Position from) {
        return rank.gapWith(from.rank);
    }

    public int fileGap(final Position from) {
        return file.gapWith(from.file);
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

    public File file() {
        return file;
    }

    public Rank rank() {
        return rank;
    }
}
