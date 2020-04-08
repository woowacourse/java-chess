package chess.model.domain.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import util.NullChecker;

public class BoardSquare {

    public static final int MIN_FILE_AND_RANK_COUNT = 1;
    public static final int MAX_FILE_AND_RANK_COUNT;
    private static final Map<String, BoardSquare> CACHE;
    private static final String SQUARE_NOT_CACHED_EXCEPTION_MESSAGE = "잘못된 입력 - Square 인자";

    private final File file;
    private final Rank rank;

    static {
        CACHE = Collections.unmodifiableMap(Arrays.stream(File.values())
            .flatMap(file -> Arrays.stream(Rank.values())
                .map(rank -> new BoardSquare(file, rank)))
            .collect(Collectors.toMap(BoardSquare::getName, Function.identity())));

        MAX_FILE_AND_RANK_COUNT = Integer.max(File.values().length, Rank.values().length);
    }

    private BoardSquare(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static BoardSquare of(String location) {
        NullChecker.validateNotNull(location);
        return CACHE.keySet().stream()
            .filter(key -> key.equals(location))
            .map(CACHE::get)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(SQUARE_NOT_CACHED_EXCEPTION_MESSAGE));
    }

    public static BoardSquare of(File file, Rank rank) {
        NullChecker.validateNotNull(file, rank);
        return BoardSquare.of(file.getName() + rank.getName());
    }

    public String getName() {
        return file.getName() + rank.getName();
    }

    public boolean hasIncreased(int fileIncrement, int rankIncrement) {
        if (file.hasNextIncrement(fileIncrement) && rank.hasNextIncrement(rankIncrement)) {
            File nextIncrementFile = file.findIncrement(fileIncrement);
            Rank nextIncrementRank = rank.findIncrement(rankIncrement);
            return CACHE.containsKey(nextIncrementFile.getName() + nextIncrementRank.getName());
        }
        return false;
    }

    public BoardSquare getIncreased(int fileIncrement, int rankIncrement) {
        return BoardSquare.of(file.findIncrement(fileIncrement), rank.findIncrement(rankIncrement));
    }

    public int getFileCompare(BoardSquare boardSquare) {
        return Integer.compare(file.compareTo(boardSquare.file), 0);
    }

    public int getRankCompare(BoardSquare boardSquare) {
        return Integer.compare(rank.compareTo(boardSquare.rank), 0);
    }

    public boolean isSameFile(BoardSquare boardSquare) {
        return this.file == boardSquare.file;
    }

    public boolean isLastRank() {
        return this.rank == Rank.FIRST || this.rank == Rank.EIGHTH;
    }

    @Override
    public String toString() {
        return file.getName() + rank.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BoardSquare that = (BoardSquare) o;
        return file == that.file &&
            rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public boolean isSameRank(Rank rank) {
        return this.rank == rank;
    }
}

