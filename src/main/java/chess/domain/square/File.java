package chess.domain.square;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static chess.domain.square.Square.MAX_NUM;
import static chess.domain.square.Square.MIN_NUM;

public class File {
    private static Map<Integer, File> CACHE = new HashMap<>();

    private int number;
    private String name;

    public File(int number, char name) {
        this.number = number;
        this.name = Character.toString(name);
    }

    static {
        IntStream.rangeClosed(1, 8)
                .forEach(i -> CACHE.putIfAbsent(i, new File(i, (char) ('a' + i - 1))));
    }

    public static File of(char letter) {
        return File.of(letter - 'a' + 1);
    }

    public static File of(int number) {
        if (!CACHE.containsKey(number)) {
            throw new IllegalArgumentException("잘못된 File의 입력입니다");
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
