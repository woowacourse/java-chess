package chess.domain.board;

import util.NullChecker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BoardSquare {

    private final static Map<String, BoardSquare> CACHE;
    public final static int MAX_FILE_AND_RANK_COUNT = Integer.max(Rank.count(), File.count());
    public final static int MIN_FILE_AND_RANK_COUNT = 1;

    static {
        Map<String, BoardSquare> cache = new HashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                cache.put(file.getName() + rank.getName(), new BoardSquare(file, rank));
            }
        }
        CACHE = Collections.unmodifiableMap(cache);
    }

    private final File file;
    private final Rank rank;

    private BoardSquare(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static BoardSquare of(String location) {
        NullChecker.validateNotNull(location);
        if (CACHE.containsKey(location)) {
            return CACHE.get(location);
        }
        throw new IllegalArgumentException("잘못된 square의 입력입니다");
    }

    public static BoardSquare of(File file, Rank rank) {
        NullChecker.validateNotNull(file, rank);
        String key = file.getName() + rank.getName();
        return BoardSquare.of(key);
    }

    private boolean hasCacheAdded(int fileIncrementBy, int rankIncrementBy) {
        if (file.hasNextIncrement(fileIncrementBy) && rank.hasNextIncrement(rankIncrementBy)) {
            File incrementFile = file.findAddIncrement(fileIncrementBy);
            Rank incrementRank = rank.findAddIncrement(rankIncrementBy);
            return CACHE.containsKey(incrementFile.getName() + incrementRank.getName());
        }
        return false;
    }

    public BoardSquare getAddIfInBoundaryOrMyself(int fileIncrementBy, int rankIncrementBy) {
        if (hasCacheAdded(fileIncrementBy, rankIncrementBy)) {
            File incrementFile = file.findAddIncrement(fileIncrementBy);
            Rank incrementRank = rank.findAddIncrement(rankIncrementBy);
            return BoardSquare.of(incrementFile, incrementRank);
        }
        return this;
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

}

