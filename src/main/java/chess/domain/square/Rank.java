package chess.domain.square;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static chess.domain.square.Square.MAX_NUM;
import static chess.domain.square.Square.MIN_NUM;

public class Rank {
    private static Map<Integer, Rank> CACHE = new HashMap<>();

    private int number;
    private String name;

    public Rank(int number, String name) {
        this.number = number;
        this.name = name;
    }

    static {
        IntStream.rangeClosed(1, 8)
                .forEach(i -> CACHE.putIfAbsent(i, new Rank(i, Integer.toString(i))));
    }

    public static Rank of(int number) {
        if (!CACHE.containsKey(number)) {
            throw new IllegalArgumentException("잘못된 Rank의 입력입니다");
        }
        return CACHE.get(number);
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public boolean validateIncrementNumber(int NumberIncrementBy) {
        return (number + NumberIncrementBy) <= MAX_NUM
                && (number + NumberIncrementBy) >= MIN_NUM;
    }
}
