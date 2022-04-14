package chess.model.board;

import chess.model.File;
import chess.model.Rank;
import chess.model.strategy.move.Direction;
import chess.model.strategy.move.Distance;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Square {
    private static final Map<String, Square> SQUARE_CACHE = new HashMap<>();
    private static final int SQUARE_NAME_LENGTH = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final File file;
    private final Rank rank;

    private Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(String squareName) {
        if (squareName.length() != SQUARE_NAME_LENGTH) {
            throw new IllegalArgumentException("잘못된 위치를 입력하였습니다.");
        }
        File file = File.valueOf(String.valueOf(Character.toUpperCase(squareName.charAt(FILE_INDEX))));
        Rank rank = Rank.of(Character.getNumericValue(squareName.charAt(RANK_INDEX)));
        return Square.of(file, rank);
    }

    public static Square of(File file, Rank rank) {
        String squareName = file.getName() + rank.getName();
        if (!SQUARE_CACHE.containsKey(squareName)) {
            SQUARE_CACHE.put(squareName, new Square(file, rank));
        }
        return SQUARE_CACHE.get(squareName);
    }

    private static int gcd(int fileDistance, int rankDistance) {
        fileDistance = Math.abs(fileDistance);
        rankDistance = Math.abs(rankDistance);
        int bigger = Math.max(fileDistance, rankDistance);
        int smaller = Math.min(fileDistance, rankDistance);
        while (smaller > FILE_INDEX) {
            int tmp = bigger;
            bigger = smaller;
            smaller = tmp % smaller;
        }
        return bigger;
    }

    public Distance getDistance(Square target) {
        Direction direction = this.findDirection(target);
        Square tempSquare = this;
        int movedCount = FILE_INDEX;
        while (!tempSquare.equals(target)) {
            tempSquare = tempSquare.move(direction);
            movedCount++;
        }
        return Distance.of(movedCount);
    }

    public Square move(Direction direction) {
        return Square.of(file.add(direction.getRow()), rank.add(direction.getCol()));
    }

    public Direction findDirection(Square source) {
        int fileDistance = file.calculateGap(source.file);
        int rankDistance = rank.calculateGap(source.rank);
        int gcd = gcd(fileDistance, rankDistance);
        return Direction.of(fileDistance / gcd, rankDistance / gcd);
    }

    public boolean isSameFile(Square other) {
        return this.file.equals(other.file);
    }

    public boolean isSameRank(Rank startRank) {
        return this.rank.equals(startRank);
    }

    public boolean isDifferent(Square other) {
        return !this.equals(other);
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
        return file == square.file && rank == square.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public String getName() {
        return file.getName() + rank.getName();
    }
}
