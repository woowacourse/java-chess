package chess.domain.position;

public enum File {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H,
    ;

    public static File of(final int x) {
        try {
            File[] values = File.values();
            return values[x - 1];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("위치의 가로, 세로 범위는 각각 1 ~ 8이여야 합니다.");
        }
    }

    public File move(final int distance) {
        if (distance > 0) {
            return this.right(distance);
        }
        if (distance < 0) {
            return this.left(Math.abs(distance));
        }

        return this;
    }

    public boolean canMove(final int distance) {
        if (distance > 0) {
            return this.canRight(distance);
        }
        if (distance < 0) {
            return this.canLeft(Math.abs(distance));
        }

        return true;
    }

    public File right() {
        File[] values = File.values();
        if (canRight()) {
            return values[ordinal() + 1];
        }

        throw new IllegalStateException("이동이 불가능합니다.");
    }

    public File right(final int count) {
        File file = this;
        for (int i = 0; i < count; i++) {
            file = file.right();
        }
        return file;
    }

    public boolean canRight() {
        int rightIndex = ordinal() + 1;
        return rightIndex < File.values().length;
    }

    public boolean canRight(final int count) {
        File file = this;

        for (int i = 0; i < count; i++) {
            if (!file.canRight()) {
                return false;
            }
            file = file.right();
        }

        return true;
    }

    public File left() {
        File[] values = File.values();
        if (canLeft()) {
            return values[ordinal() - 1];
        }

        throw new IllegalStateException("이동이 불가능합니다.");
    }

    public File left(final int count) {
        File file = this;
        for (int i = 0; i < count; i++) {
            file = file.left();
        }
        return file;
    }

    public boolean canLeft() {
        int leftIndex = ordinal() - 1;
        return leftIndex >= 0;
    }

    public boolean canLeft(final int count) {
        File file = this;

        for (int i = 0; i < count; i++) {
            if (!file.canLeft()) {
                return false;
            }
            file = file.left();
        }

        return true;
    }

    public boolean canMoveOneSpace(final File file) {
        boolean canMoveOne = canLeft() && left() == file;

        if (canRight() && right() == file) {
            canMoveOne = true;
        }

        return canMoveOne;
    }
}
