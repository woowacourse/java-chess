package chess.domain.position;

import java.util.Arrays;

public enum File {
    A('a', 1),
    B('b', 2),
    C('c', 3),
    D('d', 4),
    E('e', 5),
    F('f', 6),
    G('g', 7),
    H('h', 8);

    private final char symbol;
    private final int number;

    File(char symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    public static File of(String file) {
        return Arrays.stream(values())
                .filter(f -> f.symbol == file.toLowerCase().charAt(0))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 x 좌표값을 입력하였습니다."));
    }

    public static File of(int file) {
        return Arrays.stream(values())
                .filter(f -> f.number == file)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 x 좌표값을 입력하였습니다."));
    }

    public int getNumber() {
        return number;
    }

    public int calculateGap(File file) {
        return this.number - file.number;
    }
}
