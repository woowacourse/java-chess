package chess.domain.piece.factory;

import chess.config.BoardConfig;

import java.util.Objects;

class InitialColumn {
    private final int value;

    InitialColumn(int value) {
        if (value < BoardConfig.LINE_START || BoardConfig.LINE_END < value) {
            throw new IllegalArgumentException(String.format("%d는 %d ~ %d 내에 있지 않습니다.", value, BoardConfig.LINE_START, BoardConfig.LINE_END));
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
