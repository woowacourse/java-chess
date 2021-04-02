package chess.domain.position;

import chess.domain.utils.RegexUtils;

import java.util.Arrays;

public enum AlphaColumns {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final int asciNumber;

    AlphaColumns(char value) {
        this.asciNumber = value;
    }

    public static AlphaColumns getInstance(String value) {
        return getInstance(validCharacter(value));
    }

    public static AlphaColumns getInstance(char value) {
        return getInstance((int) value);
    }

    public static AlphaColumns getInstance(int value) {
        return Arrays.stream(AlphaColumns.values())
                .filter(alpha -> alpha.asciNumber == value)
                .findFirst()
                .orElse(null);
    }

    private static int validCharacter(String value) {
        if (RegexUtils.isValidAlphaColumn(value)) {
            return value.charAt(0);
        }
        throw new IllegalArgumentException("유효하지 않은 입력입니다. 알파벳이어야 합니다.");
    }

    public AlphaColumns movedAlpha(int value) {
        return AlphaColumns.getInstance(asciNumber + value);
    }

    public String alpha() {
        return String.valueOf((char) asciNumber);
    }

}
