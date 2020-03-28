package chess.domain.board;

import util.NullChecker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BoardSquare {

    private final static Map<String, BoardSquare> CACHE;
    private final static char FILE_START = 'a';
    private final static int FILE_COUNT = 8;
    private final static int RANK_START = 1;
    private final static int RANK_COUNT = 8;
    public final static int MAX_FILE_AND_RANK_COUNT = Integer.max(RANK_COUNT, FILE_COUNT);
    public final static int MIN_FILE_AND_RANK_COUNT = 1;

    static {
        Map<String, BoardSquare> cache = new HashMap<>();
        for (char file = FILE_START; file < FILE_COUNT + FILE_START; file++) {
            for (int rank = RANK_START; rank < RANK_START + RANK_COUNT; rank++) {
                cache.put(String.valueOf(file) + rank, new BoardSquare(file, rank));
            }
        }
        CACHE = Collections.unmodifiableMap(cache);
    }

    private final char file;
    private final int rank;

    private BoardSquare(char file, int rank) {
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

    public static BoardSquare of(BoardSquare boardSquare, int fileIncrementBy, int rankIncrementBy) {
        NullChecker.validateNotNull(boardSquare);
        return BoardSquare.of(String.valueOf((char) (boardSquare.file + fileIncrementBy)) + (boardSquare.rank + rankIncrementBy));
    }

    private boolean hasCacheAdded(int fileIncrementBy, int rankIncrementBy) {
        char fileAdd = (char) (file + fileIncrementBy);
        int rankAdd = rank + rankIncrementBy;
        return CACHE.containsKey(String.valueOf(fileAdd) + rankAdd);
    }

    public BoardSquare addIfInBoundary(int fileIncrementBy, int rankIncrementBy) {
        if (hasCacheAdded(fileIncrementBy, rankIncrementBy)) {
            return BoardSquare.of(this, fileIncrementBy, rankIncrementBy);
        }
        return this;
    }

    @Override
    public String toString() {
        return "{" + file + rank + '}';
    }

    public int getFileCompare(BoardSquare boardSquare) {
        return Integer.compare(this.file, boardSquare.file);
    }

    public int getRankCompare(BoardSquare boardSquare) {
        return Integer.compare(this.rank, boardSquare.rank);
    }

    public boolean isSameFile(BoardSquare boardSquare) {
        return this.file == boardSquare.file;
    }
}

