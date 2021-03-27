package chess.domain.position;

import chess.domain.utils.RegexUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlphaColumn {
    public static final AlphaColumn ERROR = new AlphaColumn(0);
    public static final int START_NUMBER = 'a';
    public static final int INITIAL_CAPACITY = 8;
    private static final Map<Integer, AlphaColumn> cache = new HashMap<>(INITIAL_CAPACITY);

    static {
        for (int i = START_NUMBER; i < INITIAL_CAPACITY + START_NUMBER; i++) {
            cache.put(i, new AlphaColumn(i));
        }
    }

    private final int asciNumber;

    private AlphaColumn(int i) {
        this.asciNumber = i;
    }

    public static AlphaColumn valueOf(String value) {
        return valueOf(validCharacter(value));
    }

    public static AlphaColumn valueOf(char value) {
        return valueOf((int) value);
    }

    public static AlphaColumn valueOf(int value) {
        if (cache.containsKey(value)) {
            return cache.get(value);
        }
        return ERROR;
    }

    private static int validCharacter(String value) {
        if (RegexUtils.isValidAlphaColumn(value)) {
            return value.charAt(0);
        }
        throw new IllegalArgumentException("유효하지 않은 입력입니다. 알파벳이어야 합니다.");
    }

    public static List<AlphaColumn> values() {
        return new ArrayList<>(cache.values());
    }

    public String movedAlpha(int value) {
        return AlphaColumn.valueOf(asciNumber + value).alpha();
    }

    public String alpha() {
        return String.valueOf((char) asciNumber);
    }

    @Override
    public String toString() {
        return String.valueOf((char) asciNumber);
    }


}
