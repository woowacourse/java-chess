package domain.piece.attribute.point;

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

    public static boolean isInBoundary(final int index) {
        return index >= 0 && index < values().length;
    }

    File(final char value) {
        this.value = value;
    }

    public static File from(final char value) {
        for (final var file : File.values()) {
            if (file.value == value) {
                return file;
            }
        }
        throw new IllegalArgumentException(String.format("%c는 파일에 존재하지 않습니다.", value));
    }

    public static File findByIndex(final int ordinalIndex) {
        return values()[ordinalIndex];
    }



    public boolean isFarLeft() {
        return ordinal() == 0;
    }

    public boolean isFarRight() {
        return ordinal() == values().length - 1;
    }

    public boolean canMoveLeft(final int step) {
        return ordinal() - step >= 0;
    }

    public boolean canMoveRight(final int step) {
        return ordinal() + step < values().length;
    }

    public File moveLeft(final int step) {
        if (canMoveLeft(step)) {
            return values()[ordinal() - step];
        }
        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public File moveLeft() {
        return moveLeft(1);
    }

    public File moveRight(final int step) {
        if (canMoveRight(step)) {
            return values()[ordinal() + step];
        }
        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public File moveRight() {
        return moveRight(1);
    }

}
