package chess.domain.square;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Square {

    private static final ConcurrentHashMap<String, Square> squareCache = new ConcurrentHashMap<>();
    private final File file;
    private final Rank rank;

    private Square(final File file, final Rank rank) {
        this.rank = rank;
        this.file = file;
    }

    public static Square from(final String square) {
        return squareCache.computeIfAbsent(square, s -> {
            String[] splitSquare = s.split("");
            File file = File.from(splitSquare[0]);
            Rank rank = Rank.from(splitSquare[1]);
            return new Square(file, rank);
        });
    }

    public static Square of(final File file, final Rank rank) {
        String squareKey = generateSquareKey(file, rank);
        return squareCache.computeIfAbsent(squareKey, k -> new Square(file, rank));
    }

    private static String generateSquareKey(final File file, final Rank rank) {
        return file.name() + rank.name();
    }

    public Square move(final int fileMoveStep, final int rankMoveStep) {
        String newSquareKey = generateSquareKey(fileMoveStep, rankMoveStep);
        return squareCache.computeIfAbsent(newSquareKey, k -> {
            File newFile = file.move(fileMoveStep);
            Rank newRank = rank.move(rankMoveStep);
            return new Square(newFile, newRank);
        });
    }

    private String generateSquareKey(final int fileMoveStep, final int rankMoveStep) {
        int newFileIndex = getFileIndex() + fileMoveStep;
        int newRankIndex = getRankIndex() + rankMoveStep;
        return File.values()[newFileIndex].name() + Rank.values()[newRankIndex].name();
    }

    public int getFileIndex() {
        return file.index();
    }

    public int getRankIndex() {
        return rank.index();
    }

    public String getName() {
        return file.name() + (rank.index() + 1);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Square square = (Square) o;
        return rank == square.rank && file == square.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
