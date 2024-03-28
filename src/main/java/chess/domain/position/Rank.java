package chess.domain.position;

public enum Rank {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    ;

    public static Rank of(final int y) {
        try {
            Rank[] values = Rank.values();
            return values[y - 1];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("위치의 가로, 세로 범위는 각각 1 ~ 8이여야 합니다.");
        }
    }

    public Rank move(final int distance) {
        if (distance > 0) {
            return this.up(distance);
        }
        if (distance < 0) {
            return this.down(Math.abs(distance));
        }

        return this;
    }

    public boolean canMove(final int distance) {
        if (distance > 0) {
            return this.canUp(distance);
        }
        if (distance < 0) {
            return this.canDown(Math.abs(distance));
        }

        return true;
    }

    public Rank up() {
        Rank[] values = Rank.values();
        if (canUp()) {
            return values[ordinal() + 1];
        }

        throw new IllegalStateException("이동이 불가능합니다.");
    }

    public Rank up(final int count) {
        Rank rank = this;
        for (int i = 0; i < count; i++) {
            rank = rank.up();
        }
        return rank;
    }

    public boolean canUp() {
        int leftIndex = ordinal() + 1;
        return leftIndex < Rank.values().length;
    }

    public boolean canUp(final int count) {
        Rank rank = this;

        for (int i = 0; i < count; i++) {
            if (!rank.canUp()) {
                return false;
            }
            rank = rank.up();
        }

        return true;
    }

    public Rank down() {
        Rank[] values = Rank.values();
        if (canDown()) {
            return values[ordinal() - 1];
        }
        throw new IllegalStateException("이동이 불가능합니다.");
    }

    public Rank down(final int count) {
        Rank rank = this;
        for (int i = 0; i < count; i++) {
            rank = rank.down();
        }
        return rank;
    }

    public boolean canDown() {
        int rightIndex = ordinal() - 1;
        return rightIndex >= 0;
    }

    public boolean canDown(final int count) {
        Rank rank = this;

        for (int i = 0; i < count; i++) {
            if (!rank.canDown()) {
                return false;
            }
            rank = rank.down();
        }
        return true;
    }

    public boolean canMoveOneSpace(final Rank rank) {
        boolean canMoveOne = canDown() && down() == rank;

        if (canUp() && up() == rank) {
            canMoveOne = true;
        }

        return canMoveOne;
    }
}
