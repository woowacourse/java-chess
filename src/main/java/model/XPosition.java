package model;

import java.util.HashMap;
import java.util.Map;

public class XPosition {
    private static final String X_POSITION_RANGE_EXCEPTION_MESSAGE = "xPosition 범위가 초과하였습니다.";
    private static final char MAX_ALPHABET = 'h';
    private static final char MIN_ALPHABET = 'a';
    private static final int a_ASCII = 96;
    private static Map<Character, XPosition> xPositions = new HashMap<>();
    private final int xPosition;

    static {
        for(char alphabet = MIN_ALPHABET; alphabet <= MAX_ALPHABET; alphabet++) {
            xPositions.put(alphabet, new XPosition(alphabet - a_ASCII));
        }
    }

    private XPosition(final int xPosition) {
        this.xPosition = xPosition;
    }

    public static XPosition valueOf(final char alphabet) {
        if (alphabet < MIN_ALPHABET || alphabet > MAX_ALPHABET) {
            throw new IllegalArgumentException(X_POSITION_RANGE_EXCEPTION_MESSAGE);
        }

        return xPositions.get(alphabet);
    }

    public int getValue() {
        return xPosition;
    }
}
