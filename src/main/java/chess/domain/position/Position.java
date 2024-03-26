package chess.domain.position;

import java.util.*;

public class Position {
    private static final Map<String, Position> CACHE;

    static {
        CACHE = new HashMap<>();
        for (File file : File.values()) {
            createRankPosition(file);
        }
    }

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return CACHE.get(toKey(file, rank));
    }

    public static Position from(String moveCommand) {
        return CACHE.get(moveCommand.toLowerCase());
    }

    private static void createRankPosition(File file) {
        for (Rank rank : Rank.values()) {
            CACHE.put(toKey(file, rank), new Position(file, rank));
        }
    }

    private static String toKey(File file, Rank rank) {
        return file.fileSymbol + rank.rankRow;
    }

    public int calculateFileDifference(Position target) {
        return file.calculateDifference(target.file);
    }

    public int calculateRankDifference(Position target) {
        return rank.calculateDifference(target.rank);
    }

    public Position move(int fileUnit, int rankUnit) {
        File movedFile = file.move(fileUnit);
        Rank movedRank = rank.move(rankUnit);

        return Position.of(movedFile, movedRank);
    }

    public boolean isRank(Rank rank) {
        return this.rank.equals(rank);
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
