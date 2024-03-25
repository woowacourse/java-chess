package domain.chess;

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
    private final char value;

    File(final char value) {
        this.value = value;
    }

    public static File from(final char value) {
        return Arrays.stream(values())
                     .filter(file -> file.value == value)
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException(String.format("%c는 파일에 존재하지 않습니다.", value)));
    }

    public boolean isLeftEnd() {
        return ordinal() == 0;
    }

    public boolean isRightEnd() {
        return ordinal() + 1 == values().length;
    }

    public File moveLeft(final int count) {
        if (canMoveLeft(count)) {
            return values()[ordinal() - count];
        }
        throw new IllegalStateException(String.format("%d 만큼 왼쪽으로 움직일 수 없습니다.", count));
    }

    public boolean canMoveLeft(final int count) {
        return ordinal() - count >= 0;
    }

    public File moveRight(final int count) {
        if (canMoveRight(count)) {
            return values()[ordinal() + count];
        }
        throw new IllegalStateException(String.format("%d 만큼 오른쪽으로 움직일 수 없습니다.", count));
    }

    public boolean canMoveRight(final int count) {
        return ordinal() + count < values().length;
    }

}
