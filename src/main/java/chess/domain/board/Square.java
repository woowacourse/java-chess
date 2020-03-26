package chess.domain.board;

import util.NullChecker;

import java.util.*;

public class Square {
    private final static Map<String, Square> CACHE = new HashMap<>();

    static {
        for (char file = 'a'; file <= 'h'; file++) {
            for (int rank = 1; rank <= 8; rank++) {
                CACHE.put(String.valueOf(file) + rank, new Square(file, rank));
            }
        }
    }

    private final char file;
    private final int rank;

    private Square(char file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(String location) {
        NullChecker.validateNotNull(location);
        if (CACHE.containsKey(location)) {
            return CACHE.get(location);
        }
        throw new IllegalArgumentException("잘못된 square의 입력입니다");
    }

    public static Square of(Square square, int fileIncrementBy, int rankIncrementBy) {
        NullChecker.validateNotNull(square);
        return Square.of(String.valueOf((char) (square.file + fileIncrementBy)) + (square.rank + rankIncrementBy));
    }

    private boolean hasCacheAdded(int fileIncrementBy, int rankIncrementBy) {
        char fileAdd = (char) (file + fileIncrementBy);
        int rankAdd = rank + rankIncrementBy;
        return CACHE.containsKey(String.valueOf(fileAdd) + rankAdd);
    }

    public Square addIfInBoundary(int fileIncrementBy, int rankIncrementBy) {
        if (hasCacheAdded(fileIncrementBy, rankIncrementBy)) {
            return Square.of(this, fileIncrementBy, rankIncrementBy);
        }
        return this;
    }

    @Override
    public String toString() {
        return "{" + file + rank + '}';
    }

    public char getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public boolean isJustSameFile(Square square) {
        return (this != square) && (this.file == square.file);
    }

    public Set<Square> getAll() {
        return new HashSet<>(CACHE.values());
    }
}
