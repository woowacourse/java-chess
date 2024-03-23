package chess.domain.position;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Square {
    private static final String INVALID_RANK_ERROR = "위치가 존재하지 않습니다. 다시 입력하세요.";
    private static final Map<String, Square> POOL;

    static {
        POOL = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> new Square(file, rank)))
                .collect(Collectors.toMap(it -> toKey(it.file, it.rank), Function.identity()));
    }

    private final File file;
    private final Rank rank;

    private static String toKey(File file, Rank rank) {
        return file.symbol() + rank.value();
    }

    private Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(File file, Rank rank) {
        return POOL.get(toKey(file, rank));
    }

    public static Square findByName(String name) {
        validateInPool(name);
        return POOL.get(name);
    }

    private static void validateInPool(String name) {
        if (!POOL.containsKey(name)) {
            throw new IllegalArgumentException(INVALID_RANK_ERROR);
        }
    }

    public Square moveVertical(int index) {
        return Square.of(file, rank.moveVertical(index));
    }

    public Square moveHorizontal(int index) {
        return Square.of(file.moveHorizontal(index), rank);
    }

    public Square moveDiagonal(int horizontalIndex, int verticalIndex) {
        return Square.of(file.moveHorizontal(horizontalIndex), rank.moveVertical(verticalIndex));
    }

    public SquareDifferent calculateDiff(Square another) {
        return new SquareDifferent(rank.calculateDiff(another.rank), file.calculateDiff(another.file));
    }

    public boolean isPawnStartSquare() {
        return rank.equals(Rank.TWO) || rank.equals(Rank.SEVEN);
    }
}
