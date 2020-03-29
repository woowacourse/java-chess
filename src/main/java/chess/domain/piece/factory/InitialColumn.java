package chess.domain.piece.factory;

import java.util.Objects;

class InitialColumn {
    private static final int LINE_START_INDEX = 1;
    private static final int LINE_END_INDEX = 8;
    private final int value;

    InitialColumn(int value) {
        if (value < LINE_START_INDEX || LINE_END_INDEX < value) {
            throw new IllegalArgumentException(String.format("%d는 %d ~ %d 내에 있지 않습니다.", value, LINE_START_INDEX, LINE_END_INDEX));
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InitialColumn that = (InitialColumn) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
