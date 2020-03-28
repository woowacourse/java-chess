package chess.domain.position;

import java.util.Arrays;

/**
 * 체스의 File
 */
public enum X {
    A(1, 'a'),
    B(2, 'b'),
    C(3, 'c'),
    D(4, 'd'),
    E(5, 'e'),
    F(6, 'f'),
    G(7, 'g'),
    H(8, 'h');

    private int x;
    private char expression;

    X(int x, char expression) {
        this.x = x;
        this.expression = expression;
    }

    public static X of(char expression) {
        return Arrays.stream(values())
            .filter(x -> x.expression == expression)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("체스의 X 축에 존재하지 않는 표현입니다."));
    }

    public int getInt() {
        return this.x;
    }

    @Override
    public String toString() {
        return "" + expression;
    }
}
