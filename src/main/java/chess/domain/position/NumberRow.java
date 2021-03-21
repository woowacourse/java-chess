package chess.domain.position;

import chess.domain.utils.RegexUtils;
import java.util.HashMap;
import java.util.Map;

public class NumberRow {
    public static final int START_NUMBER = 1;
    public static final int INITIAL_CAPACITY = 8;
    private static final Map<Integer, NumberRow> cache = new HashMap<>(INITIAL_CAPACITY);

    static{
        for (int i = START_NUMBER; i < INITIAL_CAPACITY + START_NUMBER; i++) {
            cache.put(i, new NumberRow(i));
        }
    }
    private final int number;

    private NumberRow(int i){
        this.number = i;
    }

    public static NumberRow valueOf(String value) {
        return valueOf(validNumber(value));
     }

    public static NumberRow valueOf(int value){
        if(cache.containsKey(value)){
            return cache.get(value);
        }
        throw new IllegalArgumentException("숫자 좌표가 유효하지 않습니다.");
    }

    private static int validNumber(String value){
        if(RegexUtils.isValidRowNumber(value)){
            return Integer.parseInt(value);
        }
        throw new IllegalArgumentException("유효하지 않은 입력입니다. 숫자이어야 합니다.");
    }

}
