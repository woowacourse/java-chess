package chess.domain.position;

import java.util.Arrays;

public enum File {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char symbol;

    File(char symbol) {
        this.symbol = symbol;
    }

    public static File of(String file) {
        return Arrays.stream(values())
                .filter(f -> f.symbol == file.toLowerCase().charAt(0))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 x 좌표값을 입력하였습니다."));
    }

    public static File of(int file) {
        return Arrays.stream(values())
                .filter(f -> f.symbol == (char) file)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 x 좌표값을 입력하였습니다."));
    }

    public int getSymbol() {
        return this.symbol;
    }
}
